using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Zadanie2
{
    public class Class1
    {
        public List<string> getlist()
        {
            return new List<string>
            {
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
            };
        }
        public double KLL(double one, double two)
        {
            double answer = (one / 100) * two;
            return answer;
        }
    }
}
