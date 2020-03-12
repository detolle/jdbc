package date;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

public class exo3Blob {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		Long id;
		int nb;

		/**
		 * connection
		 */
		try {

			/**
			 * property
			 */
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "biblio", "biblio");
			conn.setAutoCommit(false);
			
			sauveIMG(conn, "afpa", "d:\\cda\\afpa.jpg");
			chargeIMG(conn, "afpa", "d:\\cda\\afpa2.jpg");

			conn.rollback();
			//conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					System.exit(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void sauveIMG(Connection conn, String name, String location) throws Exception {
		File monImage = new File(location);
		//FileInputStream istreamImage = new FileInputStream(monImage);
		InputStream istreamImage = new FileInputStream(location);
		try {
			
			PreparedStatement ps = conn.prepareStatement("insert into tableImage (name, img) values (?,?)");
			try {
				ps.setString(1, name);
				ps.setBlob(2, istreamImage);
				//ps.setBinaryStream(2, istreamImage, (int) monImage.length());
				ps.executeUpdate();
			} finally {
				ps.close();
			}
		} finally {
			istreamImage.close();
		}
	}

	public static void chargeIMG(Connection conn,String name, String location) throws Exception {
		File monImage = new File(location);
		FileOutputStream ostreamImage = new FileOutputStream(monImage);

		try {
			PreparedStatement ps = conn.prepareStatement("select img from tableImage where name=?");
			ResultSet rs =null;
			try {
				ps.setString(1, name);
				rs = ps.executeQuery();

				if (rs.next()) {
					//InputStream istreamImage = rs.getBinaryStream("img");
					Blob blob = rs.getBlob("img");

					byte byteArray[] = blob.getBytes(1,(int)blob.length());
					ostreamImage.write(byteArray);
					
//					byte[] buffer = new byte[1024];
//					int length = 0;
//
//					while ((length = istreamImage.read(buffer)) != -1) {
//						ostreamImage.write(buffer, 0, length);
//					}
				}
			} finally {
				rs.close();
				ps.close();
			}
		} finally {
			ostreamImage.close();
		}
	}

//	public static void loadImage() {
//		BufferedImage bufferedImage;
//
//		try {
//			// read image file
//			// bufferedImage = ImageIO.read(new File("c:\\javanullpointer.png"));
//			bufferedImage = ImageIO.read(new File(""));
//
//			// create a blank, RGB, same width and height, and a white background
//			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), 
//																bufferedImage.getHeight(),
//																BufferedImage.TYPE_INT_RGB);
//			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
//
//			// write to jpeg file
//			// ImageIO.write(newBufferedImage, "jpg", new File("c:\\javanullpointer.jpg"));
//			// System.out.println("Done");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	public static void downloadImage() {
//		BufferedImage image = null;
//		try {
//
//			// URL url = new URL("//image/mypic.jpg");
//			// image = ImageIO.read(url);
//
////            ImageIO.write(image, "jpg",new File("C:\\out.jpg"));
////            ImageIO.write(image, "gif",new File("C:\\out.gif"));
////            ImageIO.write(image, "png",new File("C:\\out.png"));
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Done");
//	}

}
