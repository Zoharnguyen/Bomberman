package bomberman.view;

import java.util.ArrayList;
import java.util.List;

class Test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
//		list.set(1, "temp");
		for(String string : list) {
			System.out.println(string);
		}
	}
}