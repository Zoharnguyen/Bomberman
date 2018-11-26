package bomberman.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Test {
	public static void main(String[] args) {
		int [] arr = new int [100];
		Random random = new Random(System.currentTimeMillis());
		int status = 1 + random.nextInt(4);
		System.out.println(status);
		Random random1 = new Random();
		int status1 = 1 + random1.nextInt(4);
		System.out.println(status1);
	}
}