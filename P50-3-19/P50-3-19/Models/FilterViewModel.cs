using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace P50_3_19.Models
{
    public class FilterViewModel
    {
        public int? SelectId { get; private set; }
        public string SelectEmail { get; private set; }

        public FilterViewModel(int? id, string email)
        {
            SelectId = id;
            SelectEmail = email;
        }
    }
}
