package com.kinth.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * ComposeTest(组合测试)
 * @author GAOJJ
 * 2019/8/30 23:00
 */
public class ComposeTest {

	private List<Integer> iList = new ArrayList<Integer>();//存储输入参数（Store input parameters）
	private Map<Integer, String> map = new HashMap<Integer, String>();//存储数字对应字母（Store numeric correspondence letters）
	private Scanner sc = new Scanner(System.in);

	public ComposeTest() {
		map.put(0, "");
		map.put(1, "");
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
	}

	// 判断输入的在输入0-99是否包含以下字符，包含*#输出1
	//Determine whether the input 0-99 contains the following characters,including *# output 1
	public int content(String str) {

		boolean	bFind = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '#' || str.charAt(i) == '*') {
				bFind = true;
				continue;
			}
		}

		if (bFind == true) {
			return 1;
		}

		return 0;
	}

	// 判断输入的格式是否合法，比如 arr[] = {2,3}
	//Determine whether the input format is legitimate, such as arr []= {2,3}
	public boolean formatValid(String str) {
		int pos1 = str.indexOf("{");
		// 没有“{”不行
		//No'{'can't do it.
		if (pos1 == -1) {
			return false;
		}

		String head = str.substring(0, pos1).trim();
		// “{”前面的字符串一定要是“arr[] = ”，否则也不合法
		//The string before "{" must be "arr []=", otherwise it is not legal.
		if (!head.equals("arr []=")) {
			return false;
		}

		int pos2 = str.indexOf("}");
		// 没有“}”不行
		//Not without "}"
		if (pos2 == -1) {
			return false;
		}

		// 这个说明“}”号在“{”前面了
		//This note "}" precedes "{"
		if (pos1 >= pos2) {
			return false;
		}

		// 说明只有 {}
		//Note that only {}
		if (pos1 + 1 >= pos2) {
			return false;
		}

		String content = str.substring(pos1 + 1, pos2);
		String[] contents = content.split(",");
		if (contents.length <= 0) {
			return false;
		}

		iList.clear();
		for (int i = 0; i < contents.length; i++) {
			try {
				int value = 0;
				int ret = content(contents[i]);
				if (ret == 1) {
					value = 0;
				}
				if (ret == 0) {
					value = Integer.parseInt(contents[i]);
				}
				if(value<0 || value >99){
					System.out.print("信息：参数有误，请输入0-99。 \n");
					System.out.println("message：The parameter is wrong. Please enter 0-99.");
					return false;
				}
				//这里是转换 0 - 99 为 0 - 9
				// Here is the conversion 0 - 99 to 0 - 9
				value = value % 10;
				iList.add(value);
			} catch (Exception e) {
				System.out.print("信息：参数有误，含有非法参数。 \n");
				System.out.println("message：The parameters are incorrect and contain illegal parameters.");
				return false;
			}
		}

		return true;
	}

	//每组的组合给分析出来
	//The combination of each group is analyzed.
	public void recycle(String app, int index) {
		int number = iList.get(index);
		String letter = map.get(number);
		for (int i = 0; i < letter.length(); i++) {

			String newApp = app + letter.charAt(i);
			if (index == iList.size() - 1) {
				System.out.print(newApp + " ");
			} else {
				recycle(newApp, index + 1);
			}
		}
	}

	//方法运行入口
	//Method Running Entry
	public void run() {
		while (true) {
			System.out.print("信息：请在Input中输入参数，例如 arr []= {2,3} 或  arr []= {23} 。\n");
			System.out.println("message：Please enter parameters in Input, such as arr []= {2,3} or arr []= {23}.");
			System.out.print("Input: ");
			//接收输入参数
			//Receiving input parameters
			String line = sc.nextLine();

			if (false == formatValid(line)) {
				continue;
			}

			System.out.print("Output: ");
			recycle("", 0);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		ComposeTest composeTest = new ComposeTest();
		composeTest.run();
	}
}
