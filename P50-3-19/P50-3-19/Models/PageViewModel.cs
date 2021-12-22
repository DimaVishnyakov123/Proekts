using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace P50_3_19.Models
{
    public class PageViewModel
    {
        public int PageNumber { get; private set; } //Текущая страница
        public int TotalPage { get; private set; } //Общие кол-во страниц

        public PageViewModel (int count, int pageNumber, int pageSize)
        {
            PageNumber = pageNumber;
            TotalPage = (int)Math.Ceiling(count / (double)pageSize); 
        }

       public bool HasPreviousPage
       {
            get { return (PageNumber > 1); }
       }
       public bool HasNextPage
       {
            get { return (PageNumber < TotalPage); }
       }

    }
}
