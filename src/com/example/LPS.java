package com.example;

/*
 * �������ַ�����Ϊ��С���ַ����������
 * ��ȡ�����������˼·������ʼ�����洢�������ȵ����������飬
 * 					����������1�ĳ���ʱ����λ�ã�i,j����ԭ�ַ���������Ӧ���ַ�һ����ͬ��
 * 					��¼�������ַ����������ϱ�����ֱ����ȫ�����������Ϊֹ��
 */
public class LPS {
	// ����������������еĳ���
	public static int[][] L;
	public static String seq = "12345321";

	public static int max(int x, int y) {
		return (x > y) ? x : y;
	}

	public static int lps(int length, int k, String seq) {
		if (k == 1) {
			return 1;
		}
		int i, j;

		// k��ʾ�м�����ͬ��Ԫ��
		lps(length, k - 1, seq);
		for (i = 0; i < length - k + 1; i++) {
			j = i + k - 1;
			if (seq.charAt(i) == seq.charAt(j) && k == 2) {
				// ��kΪ2ʱ���ֻ������Ԫ��
				L[i][j] = 2;
			} else if (seq.charAt(i) == seq.charAt(j)) {
				// ��2��ָ������������ͬ��Ԫ��
				L[i][j] = L[i + 1][j - 1] + 2;
			} else {
				L[i][j] = max(L[i][j - 1], L[i + 1][j]);
			}
		}
		return L[0][length - 1];
	}

	public static void main(String args[]) {
		int seq_length = seq.length();
		// ���ڱ��������еĳ���
		L = new int[seq_length][seq_length];
		// �Խ������Ԫ�ض�����ͬ��
		for (int i = 0; i < seq_length; i++) {
			L[i][i] = 1;
		}
		int lps_length = lps(seq_length, seq_length, seq);
		// ���ڴ洢�������
		String lps_str = "";

		int length = L.length;
		output(length);

		if (lps_length == 1) {
			lps_str = seq.charAt(0) + "";
		} else {
			lps_str = getLPS(length);
		}
		System.out.println(seq + " ����Ļ���������Ϊ   " + lps_str + "  ����Ϊ��" + ""
				+ lps_length);
	}

	// ��ȡ�����������
	public static String getLPS(int length) {
		String lps_str = "";
		int length1 = 0;
		int temp = 0;// ���ڴ洢ǰһ������ֵ
		// ��ȡ�������
		for (int i = length - 1; i >= 0; i--) {
			for (int j = 0; j < length; j++) {
				length1 = L[i][j];
				if (length1 > 1) {
					// �ж�temp��Ϊ���ܹ���ֹ��ȡ3������2����������������ʱ��λ�����
					if (temp == 3 && Math.abs(i - j) == 2) {
						lps_str = " ";
						lps_str = seq.charAt(i) + "" + seq.charAt(i + 1)
								+ seq.charAt(j);
					} else if (temp == 2 && Math.abs(i - j) == 1) {
						lps_str = " ";
						lps_str = seq.charAt(i) + lps_str + seq.charAt(j);
					} else {
						if (length1 == 3 && length1 > temp) {
							lps_str = seq.charAt(i) + "" + seq.charAt(i + 1)
									+ seq.charAt(j);
							temp = length1;
							break;
						} else if ((length1 == 2 || length1 > 3)
								&& length1 > temp) {
							lps_str = seq.charAt(i) + lps_str + seq.charAt(j);
							temp = length1;
							break;
						}
					}
				}
			}
		}
		return lps_str;
	}

	public static void output(int length) {
		// �����������
		System.out.println("������г������飺");
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (j != 0 && j % (length - 1) == 0) {
					System.out.println(L[i][j]);
				} else {
					System.out.print(L[i][j] + " ");
				}
			}
		}
	}
}
