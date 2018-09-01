package eleven.lc.gigi.configure;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091600524472";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCaMwgh1L0OIHtrMqfguq74yDPTLIOU5KVdp4NnkfEIc3Wj74lfRBuT9DAR0BY4wU/aYucCFAQUZRMs04j9HGG4s24pw++T6oPTrUAMRSWkU7V0LD5KhmhOqt0uAS4JWuUJgRZgxX9uMVrXEHGq0GFf7DnwNwQ1Ap1135h9j3GeDcSfsdMv+jCfYzwI3F+itdJZi40Z+oN6ckU9Pj6gBZ6fbDxvg6OYkU/6U3IDpG1vqS47fa7ApNqunZ9zjz2TG1XYAh8MLtNbpk1VKNvbBxERLIfjB/bu1aH3D8wwmt9w3AIU2m9X8bCvrWw3D9MIlcoxULoFIB7TK9UjqLoPauGxAgMBAAECggEAd/LijI6UUfzqhMTu2l3d5iC2T7r80EHccTHxgguA1/YYuOGIltbALSNykSixYIzftR3iXTd8xPCrLd+PXHVLfwX02ABWyVVQHsVSYTkYwu9q42fXPNm5Ihi/3R2F1jkimXYVFkoLiohSBaIBCcL3PErh9v6VAzd5jUvVrzZ6R1txSny9OHbWDY9eCOBh83oev+BQMj2tjezLDj871XxXFQQsrmp085gVX7z7c2ShGdfZmGYNLMpehvLal+KMsi0o4opqe4a5sjOtjYQ2kePDCONpS6uDjOqhy1v8TSQ0Vf1E54UbdUhuuQ2Cs1V8PvDlcXYqkdBW66v6Tsu7Pq50UQKBgQDJJ5qSDQYdNqpXoU6dudvyXCbNsCW/pg0qGpgeoMIOtxO/eaQ1lN7oIlwcD5dV6dbIaywynra9t7ZyNfWqBPUvCXmv/+ol2kyWJM+olLkc8Rq52kOFAftFTeAx196D0cHHmF31CkFH93almw3xZZ0LsahzVrCJ2SUiDbHwkkFWOwKBgQDEPf20xHJyPAsMGAUYmlAxNXWKHJHbxkhWZRAXA9KYCSZ8GpMlJq6avkORPwht6++V/+Jrd3rhpJo9COhkY5Oqtw45OhliuCOVjvIygDOdpoPYCrYwCjpw9Ui2ZssslqNX2TbxrzqQCobH5AQNheaqHoq/wwJ9UJ+v4M4usROtAwKBgHTT8psmw0lOSYrRneZPXuYZDVRjHNl9KKIuhfEr7uk6/VY04OFZ0EjEQ/jsOTnlsR//1tCji4EOZQFaQ472iCxEdY95s9U+P0LoqE7B6e1cPuh4UsS6TOSIgVCRjqPCvKge31sFFLsmh5tRPbzZBmgXS72JOMkm+8EWcSVG3HmnAoGAfmtdBFXFUHgDud/DcK0AWiMRPE7ZW9ZphVaHvfvVb+H9K9fG0RkRRFirmGcd3AoxBtWYHBkVTUVHo2gYpYtMdp6EiDM4ZVMwgUlcF1Jlbfr2VnfYFCySp3AsTmz8GBZW1eH1tk5nEKHnX+AxCDEpfpA8W/qZoPcOWDQfPw8OGNcCgYAFQrybZLbvBpqY0XmVNKVN62KSOxs7xpQH6HTXYaTzFnVa1Yqwp9nkqMrAmigeLQhRUI8dgHWYfSjpc50tLGtbcRHdc46yQzfYxaayqOZz85TIOwBktvxWje/xt8KKN8p9bpr/ZRRUsaveRSsTA8hBp3xU0K17/NR9QSmb52AcSA==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwIZGP2+k6zqDvfjpT35XkXJ/zs21vFM2D3wJMfdACE1gJflPXvvcYAhWdwGT5IHaHuVXhGTzKVZ7CcetmuD7WS7yvtmAHkQZAVWZL0iXXHjGD+cwS4lL1VjavgFX3Ibe3YA1zkBvTePjCCioeKbnAt2rlYkNK3w4kei2G3Uk1jsZkDr53fkoGa6rCqu1RIiwkCE6BKJkTmo/5vc+ULgvG3Ns6fgViDIv1H8Mz1un/UWeX3PZ1vXAgWMn57qKjmtMaJdB4i5dbxLuyN9ZQjnRaCQLyp06N8C9mEiOcZTlQQGIvQvjeAKb+NV70LYiJXGfYWfvQ9VdrfOonpi9Tba/1QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8989/payFail";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8989/payResult";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "/home/lc/Documents/";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

