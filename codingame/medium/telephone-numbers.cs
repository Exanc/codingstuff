using System;
using System.Linq;
using System.IO;
using System.Text;
using System.Collections;
using System.Collections.Generic;

class Solution
{
    static void Main(string[] args)
    {

        Dict root = new Dict();

        int N = int.Parse(Console.ReadLine());
        for (int i = 0; i < N; i++)
        {
            string telephone = Console.ReadLine();

            root.addNumber(telephone.ToCharArray(), 0);
        }

        Console.WriteLine(root.count());
    }

    class Dict
    {
        Dict[] nodes;
        bool[] stored;

        public Dict()
        {
            this.nodes = new Dict[10];
        }

        public void addNumber (char[] number, int depth)
        {

            if (depth >= number.Length) return;

            int digit = (int)Char.GetNumericValue(number[depth]);
            
            if (this.nodes[digit] == null)
            {
                this.nodes[digit] = new Dict();
                this.nodes[digit].addNumber(number, depth + 1);
            } else
            {
                this.nodes[digit].addNumber(number, depth + 1);
            }
        }

        public int count ()
        {
            int c = 0;
            for (int i = 0; i < 10; i++)
            {
                if (this.nodes[i] != null)
                {
                    c = c + this.nodes[i].count() + 1;
                }
            }

            return c;
        }
    }
}