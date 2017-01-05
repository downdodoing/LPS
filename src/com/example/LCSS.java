package com.example;

/*
 * ����c��������¼������еĳ���
 * ����������������¼���Ǹ�����������õ�ֵ
 */
public class LCSS {
	public final static int MAXLEN = 100;
	public static String LPS = "";

	public static void LCSLength(char[] x, char[] y, int xLength, int yLength,
			int[][] c, int b[][]) {
		int i, j;
		// ����һ��ȫ����ʼ��0����Ϊ������һ���ַ���Ϊ��ʱ������֮�䲻����������
		for (i = 0; i <= xLength; i++) {
			c[i][0] = 0;
		}
		// ����һ�г�ʼ��Ϊ0
		for (j = 1; j <= yLength; j++) {
			c[0][j] = 0;
		}
		for (i = 1; i <= xLength; i++) {
			for (j = 1; j <= yLength; j++) {
				if (x[i - 1] == y[j - 1]) {
					c[i][j] = c[i - 1][j - 1] + 1;
					b[i][j] = 0;
					// xi��=yiʱ
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
					b[i][j] = 1;
				} else {
					c[i][j] = c[i][j - 1];
					b[i][j] = -1;
				}
			}
		}
	}

	public static void PrintLCS(int b[][], char x[], int xLength, int yLength) {
		if (xLength == 0 || yLength == 0)
			return;
		// �������0���ʾ��λ������Ԫ����ͬ���
		if (b[xLength][yLength] == 0) {
			PrintLCS(b, x, xLength - 1, yLength - 1);
			LPS += x[xLength - 1];
			// ����1��ʾx��Ҫ����ƶ�һλ
		} else if (b[xLength][yLength] == 1)
			PrintLCS(b, x, xLength - 1, yLength);
		else
			PrintLCS(b, x, xLength, yLength - 1);
	}

	public static void main(String[] args) {
		int b[][] = new int[MAXLEN][MAXLEN];
		int c[][] = new int[MAXLEN][MAXLEN];

		String str_x = "qwersada";
		char x[] = str_x.toCharArray();

		String str_y = "weqasda";
		char y[] = str_y.toCharArray();

		int xLength = str_x.length();
		int yLength = str_y.length();

		LCSLength(x, y, xLength, yLength, c, b);
		PrintLCS(b, x, xLength, yLength);
		System.out.println("���������Ϊ��" + LPS);
	}
}
