package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RandomValidateCode1 {
public static final String RANDOMCODEKEY ="RANDOMVALIDATECODEKEY";
private Random random=new Random();
private String randString ="0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ";
private int width=80;     //图片的宽
private int height=26;    //图片的高
private int lineSize=100; //干扰线数量
private int stringNum=3;   //产生的随机字符数量
/**
 * 获得字体
 */
private Font getFont(){
	return new Font("Fixedsys",Font.CENTER_BASELINE,18);
	
}
/**
 * 获得颜色
 */
private Color getRandColor(int fc,int bc){
	if(fc>255) fc=255;
	if(bc>255) bc=255;
	int r=fc+random.nextInt(bc-fc-16);
	int g=fc+random.nextInt(bc-fc-14);
	int b=fc+random.nextInt(bc-fc-18);
	return new Color(r,g,b);
}
/**
 * 生成随机数图片
 */
public void getRandcode(HttpServletRequest req,HttpServletResponse resp)throws Exception{
	//创建httpsession对象
	HttpSession session=req.getSession();
	BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	Graphics g=image.getGraphics();
	g.fillRect(0, 0, width, height);//设定边框
	g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
	g.setColor(getRandColor(110,133));
	//绘制干扰线
	for (int i = 0; i <= lineSize; i++) {
		drowLine(g);
	}
	//绘制随机字符
	String randomString="";
	for (int i = 0; i <=stringNum; i++) {
		randomString =drowString(g,randomString,i);
		
	}
	//将随机产生的字符存到session中用于验证
	session.removeAttribute(RANDOMCODEKEY);
	session.setAttribute("code", randomString);
	g.dispose();
	ImageIO.write(image, "JPEG", resp.getOutputStream());
	
}
private String drowString (Graphics g,String randomString,int i){
	g.setFont(getFont());
	g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
	random.nextInt(121);
	String rand=String.valueOf(getRandomString(random.nextInt(randString.length())));
	randomString +=rand;
	g.translate(random.nextInt(3), random.nextInt(3));
	g.drawString(rand, 13*i, 16);
	return randomString;
}
/**
 * 
 * 绘制干扰线
 */
private void drowLine(Graphics g) {
	int x=random.nextInt(width);
	int y=random.nextInt(height);
	int xl=random.nextInt(13);
	int yl=random.nextInt(15);
	g.drawLine(x, y, x+xl, y+yl);
}
/**
 * 获得随机的字符
 */
public String getRandomString(int num){
	return String.valueOf(randString.charAt(num));
}

}

