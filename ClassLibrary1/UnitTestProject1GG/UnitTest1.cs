using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using ClassLibrary1;


namespace UnitTestProject1GG
{
    [TestClass]
    public class UnitTest1
    {

        [TestMethod]
        public void Меньше_семи_символов()
        {
            int expected = 1;
            string test_pswd = "pass";
            int ball = ClassLibrary1.ZXC.Check(test_pswd);
            Assert.AreEqual(expected, ball);
            
        }

        [TestMethod]
        public void Семь_символов()
        {
            int expected = 2;
            string test_pswd = "passwor";
            int ball = ClassLibrary1.ZXC.Check(test_pswd);
            Assert.AreEqual(expected, ball);
        }

        [TestMethod]
        public void Буквы_цифры()
        {
            int expected = 3;
            string test_pswd = "password1";
            int ball = ClassLibrary1.ZXC.Check(test_pswd);
            Assert.AreEqual(expected, ball);
        }

        [TestMethod]
        public void Заглавные_буквы_цифры()
        {
            int expected = 4;
            string test_pswd = "passworD1";
            int ball = ClassLibrary1.ZXC.Check(test_pswd);
            Assert.AreEqual(expected, ball);
        }

        [TestMethod]
        public void буквы_цифры_спецсимволы()
        {
            int expected = 4;
            string test_pswd = "!password1";
            int ball = ClassLibrary1.ZXC.Check(test_pswd);
            Assert.AreEqual(expected, ball);
        }

        [TestMethod]
        public void соблюдено()
        {
            int expected = 5;
            string test_pswd = "Password123!!";
            int ball = ClassLibrary1.ZXC.Check(test_pswd);
            Assert.AreEqual(expected, ball);
        }

        [TestMethod]
        public void Test()
        {
            int expected = 6;
            string test_pswd = "qwertyu1";
            int ball = ClassLibrary1.ZXC.Check(test_pswd);
            Assert.AreEqual(expected, ball);
        }
    }
}
