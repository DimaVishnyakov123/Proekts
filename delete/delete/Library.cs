using System;
using System.Collections.Generic;
using System.Linq;

namespace delete
{
	public class Library
	{
		public int id_counter = 0;
		public List<Book> Books = new();
		public Library()
		{
		}

		public void	AddBook(Book b)
        {
			b.id = id_counter++;
			Books.Add(b);
		}

		public void EditBook(Book b)
        {
			var book = Books.Where(_b => _b.id == b.id).First();
			book.name = b.name;
			book.author = b.author;
			book.year = b.year;
		}
	}
}