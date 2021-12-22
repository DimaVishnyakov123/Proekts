using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace ClassLibrary1
{
    public class ZXC
    {
        public static int Check(string pswd)
        {
            int ball = 0;

            ball += pswd.Length >= 7 ? 1 : 0;

            ball += Regex.IsMatch(pswd, ".*[^A-Za-zА-Яа-яЁё0-9]+.*") ? 1 : 0; 
            
            ball += Regex.IsMatch(pswd, ".*[A-ZА-ЯЁ]+.*") ? 1 : 0;

            ball += Regex.IsMatch(pswd, ".*[a-zа-яё]+.*") ? 1 : 0;  
            
            ball += Regex.IsMatch(pswd, ".*[0-9]+.*") ? 1 : 0;

            return ball;
        }       
        
    }
}
