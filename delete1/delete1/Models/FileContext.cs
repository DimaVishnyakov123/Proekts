using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Design;
using System.Runtime.InteropServices;

namespace delete1.Models
{
    public class FileContext : DbContext
    {
        public DbSet<FileModel> Files { get; set; }
        public FileContext()
        {
        }

        public FileContext(DbContextOptions<FileContext> options) : base(options)
        {
            Database.EnsureCreated();
        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.HasAnnotation("Relational:Collation", "en_US.utf8");

            modelBuilder.Entity<FileModel>(entity =>
            {
                entity.HasKey(e => e.Id)
                    .HasName("files_pkey");

                entity.ToTable("files_table");

                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasColumnType("character varying")
                    .HasColumnName("name");

                entity.Property(e => e.Path)
                    .IsRequired()
                    .HasColumnType("character varying")
                    .HasColumnName("path");
            });

        }
    }
}