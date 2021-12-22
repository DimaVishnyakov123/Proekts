using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using P50_3_19.Models;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using System.IO;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Hosting;

namespace P50_3_19.Controllers
{
    public class HomeController : Controller
    {
        private ApplicationContex db;
        private IWebHostEnvironment _app;
        public HomeController(ApplicationContex context, IWebHostEnvironment app)
        {
            db = context;
            _app = app;
        }

        public IActionResult Login()
        {
            return View();
        }
        [HttpPost]
        public IActionResult Login(User user)
        {
            var userLogin = db.Users.FirstOrDefault(p => p.Login == user.Login && p.Password == user.Password);
            if (userLogin != null)
            {
                HttpContext.Session.SetString("LoginUser", userLogin.Login);
                return RedirectToAction("Index");
            }
            return NotFound();
        }

        public IActionResult AddFile()
        {
            return View(db.FileModels.ToList());
        }

        [HttpPost]
        public async Task<IActionResult> AddFile(IFormFile formFile)
        {
            if (formFile != null)
            {
                string path = "/File/" + formFile.FileName;
                using (var fileStream = new FileStream(_app.WebRootPath+path, FileMode.Create))
                {
                    await formFile.CopyToAsync(fileStream);
                }
                FileModel fileModel = new FileModel
                {
                    Name = formFile.FileName,
                    Path = path
                };
                db.FileModels.Add(fileModel);
                await db.SaveChangesAsync();
                return RedirectToAction("Index");
            }
            return RedirectToAction("AddFile");
        }



        public async Task<IActionResult> Index(int? id, string login, int page = 1, StateSort sortOrder = StateSort.IdAsc)
        {
            ViewBag.LoginUser = HttpContext.Session.GetString("LoginUser");
            IQueryable<User> users = db.Users;
            //Фильтрация или поиск
            if (id != null && id > 0)
            {
                users = users.Where(p => p.Id_user == id);
            }
            if(!String.IsNullOrEmpty(login))
            {
                users = users.Where(p => p.Email.Contains(login));
            }
            //Сортировка
            switch(sortOrder)
            {
                case StateSort.IdAsc:
                    {
                        users = users.OrderBy(m => m.Id_user);
                        break;
                    }
                case StateSort.IdDesc:
                    {
                        users = users.OrderByDescending(m => m.Id_user);
                        break;
                    }
                case StateSort.EmailAsc:
                    {
                        users = users.OrderBy(m => m.Email);
                        break;
                    }
                case StateSort.EmailDesc:
                    {
                        users = users.OrderByDescending(m => m.Email);
                        break;
                    }
            }
            //Пагинация
            int pageSize = 3;
            var count = await users.CountAsync();
            var item = await users.Skip((page - 1) * pageSize).Take(pageSize).ToListAsync();

            IndexViewModel indexViewModel = new IndexViewModel
            {
                FilterViewModel = new FilterViewModel(id, login),
                SortViewModel = new SortViewModel(sortOrder),
                PageViewModel = new PageViewModel(count, page, pageSize),
                Users = item
            
            };

            return View(indexViewModel);
        }

        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Create(User user)
        {
            db.Users.Add(user);
            await db.SaveChangesAsync();
            return RedirectToAction("Index");
        }


        [HttpGet]
        [ActionName("Delete")]
        public async Task<IActionResult> ConfirmDelete(int? id)
        {
            if (id != null)
            {
                User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Id_user == id);
                if (user != null)
                    return View(user);
            }
            return NotFound();
        }

        [HttpPost]
        public async Task<IActionResult> Delete(int? id)
        {
            if (id != null)
            {
                User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Id_user == id);
                if (user != null)
                {
                    db.Users.Remove(user);
                    await db.SaveChangesAsync();
                    return RedirectToAction("Index");
                }
            }
            return NotFound();
        }

        public async Task<IActionResult> Edit(int? id)
        {
            if (id != null)
            {
                User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Id_user == id);
                if (user != null)
                    return View(user);
            }
            return NotFound();
        }

        [HttpPost]
        public async Task<IActionResult> Edit(User user)
        {
            db.Users.Update(user);
            await db.SaveChangesAsync();
            return RedirectToAction("Index");
        }

        public async Task<IActionResult> Details(int? id)
        {
            if (id != null)
            {
                User user = await db.Users.FirstOrDefaultAsync(predicate => predicate.Id_user == id);
                if (user != null)
                    return View(user);
            }
            return NotFound();
        }
    }
}
