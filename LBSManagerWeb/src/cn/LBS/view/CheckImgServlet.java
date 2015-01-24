package cn.LBS.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpg");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");

		int width = 80;
		int height = 28;

		// ����һ ����һ���ڴ���ͼƬ
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// ����� ͼƬ���Ʊ�����ɫ ---ͨ����ͼ����
		Graphics graphics = bufferedImage.getGraphics();// �õ���ͼ���� --- ����
		// �����κ�ͼ��֮ǰ ������ָ��һ����ɫ
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width, height);

		// ������ ���Ʊ߿�
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// ������ �ĸ��������
		Graphics2D graphics2d = (Graphics2D) graphics;
		// �����������
		graphics2d.setFont(new Font("����", Font.BOLD, 18));

		// String words =
		// "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		Random random = new Random();// ���������
		String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder builder = new StringBuilder();// ������

		// ����x����
		int x = 10;
		for (int i = 0; i < 4; i++) {
			// �����ɫ
			graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// ��ת -30 --- 30��
			int jiaodu = random.nextInt(60) - 30;
			// ���㻡��
			double theta = jiaodu * Math.PI / 180;

			// ����һ���������
			int index = random.nextInt(words.length()); // ��������� 0 �� length - 1
			// �����ĸ����
			char c = words.charAt(index);

			// �������� ���뻺����
			builder.append(c);

			// ��c �����ͼƬ
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 20;
		}
		// ��������֤�� ����session
		request.getSession().setAttribute("checkcode_session",
				builder.toString());

		// ������ ���Ƹ�����
		graphics.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 28; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(12);
			y1 = random.nextInt(height);
			y2 = random.nextInt(12);
			graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
		}

		// ������ͼƬ���������� ImageIO
		graphics.dispose();// �ͷ���Դ
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * ȡ��ĳһ��Χ��color
	 * 
	 * @param fc
	 *            int ��Χ����1
	 * @param bc
	 *            int ��Χ����2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		// ȡ�������ɫ
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
