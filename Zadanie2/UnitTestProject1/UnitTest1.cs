using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;
using Zadanie2;


namespace UnitTestProject1
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void Список_теста()
        {
            List<string> list = new List<string>();
            list.Add("1");
            list.Add("2");
            list.Add("3");
            list.Add("4");
            list.Add("5");
            list.Add("6");
            list.Add("7");
            Class1 c = new Class1();
            CollectionAssert.AreEqual(list, c.getlist());
        }
        [TestMethod]
        public void Процент_сходства()
        {
            double res = 0.113;
            double delta = 0.32;
            Class1 v = new Class1();
            Assert.AreEqual(res, v.KLL(34.34, 0.33),delta);
        }
        [TestMethod]
        public void Эквивалент_Списка()
        {
            List<string> list = new List<string>();
            list.Add("7");
            list.Add("2");
            list.Add("4");
            list.Add("6");
            list.Add("5");
            list.Add("3");
            list.Add("1");
            Class1 c = new Class1();
            CollectionAssert.AreEquivalent(c.getlist(), list);
        }
    }
}
