using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadanie3
{
    public class Class1
    {
        public double[] GetDiscriminant(double a, double b, double c)
        {
            double[] X = new double[2];

            double D = (b * b) - 4 * a * c;
            if (D > 0)
            {
                X[0] = (-b + Math.Sqrt(D)) / (2 * a);
                X[1] = (-b - Math.Sqrt(D)) / (2 * a);
            }
            else if (D == 0)
            {
                X[0] = -b / (2 * a);
            }
            else if (D<0)
            {
                X[0] = default;
                X[1] = default;
            }
            return X;
        }
    }
}
