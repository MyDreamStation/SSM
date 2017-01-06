package test;

public class Test {

	public static void main(String[] args) {
		System.out.println(Decode.decodeQr(QrCode.createQrcode("www.baidu.com")));
	}
}
