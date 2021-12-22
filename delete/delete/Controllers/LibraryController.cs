using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace delete.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class LibraryController : ControllerBase
    {

        private readonly Library _library;

        public LibraryController(Library library)
        {
            _library = library;
        }

        [HttpGet]
        public IEnumerable<Book> Get()
        {
            return _library.Books.ToArray();
        }

        [HttpPost]
        public void Post(Book book)
        {
            _library.AddBook(book);
        }

        [HttpPut]
        public void Put(Book book)
        {
            _library.EditBook(book);
        }

        [HttpDelete]
        public void Delete(int id)
        {
            _library.Books.RemoveAll(b => b.id == id);
        }
    }
}
