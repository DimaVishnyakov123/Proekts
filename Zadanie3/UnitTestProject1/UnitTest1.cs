using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Zadanie3;

namespace UnitTestProject1
{
    [TestClass]
    public class UnitTest1
    {        
            [TestMethod]
            public void Два_Корня()
            {
                double[] X = new double[2];
                double a = 1;
                double b = 5;
                double c = 4;
                X = new Zadanie3.Class1().GetDiscriminant(a, b, c);
               
            double[] expected = new double[2];
            expected[0] = -4;
            expected[1] = -1;

            CollectionAssert.AreEquivalent(X, expected);
            }
            [TestMethod]
            public void Один_Корень()
            {
                double[] X = new double[2];
                double a = 1;
                double b = 6;
                double c = 9;
                X = new Zadanie3.Class1().GetDiscriminant(a, b, c);
            double[] expected = new double[2];
            expected[0] = -3;
            CollectionAssert.AreEquivalent(X, expected);

        }
            [TestMethod]
            public void Нет_Корней()
            {
                double[] X = new double[2];
                double a = 2;
                double b = 4;
                double c = 7;
                X = new Zadanie3.Class1().GetDiscriminant(a, b, c);
            double[] expected = new double[2];
            expected[0] = default;
            expected[1] = default;

            CollectionAssert.AreEquivalent(X, expected);
        }
    }
    }

