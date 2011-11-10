package org.gsu.cs.util;

public class CombinationGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 4;

		for (int l = 2; l <= n; l++) {
			System.out.println(l);
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				for (int k = i; k <= j - 1; k++) {
					System.out.print(i + " " + j + " ==== ");
					System.out.print(i + " " + k + " : ");
					System.out.print(k + 1 + " " + j + " : ");
				}
				System.out.println();
			}
		}
	}
}