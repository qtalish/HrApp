package com.kgate.test;

public class LoopClass {
	public static void main(String[] args) {
		int n = 5;
		for (int i = 1; i <= n; i++) {
//			System.out.print(j);
			for (int j = i; j<=n;j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}
}
