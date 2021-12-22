using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace P50_3_19.Models
{
    public class ApplicationContex : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<FileModel> FileModels { get; set; }
        public ApplicationContex(DbContextOptions<ApplicationContex> options)
            :base (options)
        {
            Database.EnsureCreated();
        }
    }
}
