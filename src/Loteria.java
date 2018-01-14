import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;



import archivo.Archivo;

public class Loteria   {

	/**
	 * @param args
	 */
	int numeros[];

	String archivo = "Loteria.dat";
	

	public static void main(String[] args) {			
		
		if(args.length==2){
			new Loteria().processFromTo(args[0]+"-"+args[1],false);
		}else if(args.length==3){
			if(args[0].trim().equalsIgnoreCase("mayor")){
				new Loteria().processFromTo(args[1]+"-"+args[2],true);
			}
		}		
		else if(args.length==1){

			if(args[0].trim().charAt(0)=='n'){
				System.out.println("lotery numbers by order ");
			}
			else {
				new Loteria().saveFile(args[0]);
			}
		}else{
		System.out.println("uso: \n For to process from : 4487 ?\nOne only parameter save to file");
		System.out.println("For to see only numbers of mayor: mayor 4487 ?");
		}
		// Loteria l=new Loteria();
		// l.loadInterface();
		

		// for (int i = 4487; i < 4519; i++) {
		// new Loteria().saveFile(String.valueOf(i));
		// }

		// 
	}

	List<String> numeroslista = null;

	

	private InputStream inputS;
	private FileOutputStream fos;
	private DataInputStream entrada;

	private void processFromTo(String sorteo, boolean mayor) {

		int posicion1[] = new int[10];
		int posicion2[] = new int[10];
		int posicion3[] = new int[10];
		int posicion4[] = new int[10];

		int posicionserie1[] = new int[10];
		int posicionserie2[] = new int[10];
		int posicionserie3[] = new int[10];
		List<String> numeros = new ArrayList<>();
		List<String> series = new ArrayList<>();
		String serieFromTo[] = sorteo.split("-");
		String serie = null;
		for (int i = Integer.parseInt(serieFromTo[0].trim()); i <= Integer
				.parseInt(serieFromTo[1].trim()); i++) {
			FileInputStream fstream;
			try {

				fstream = new FileInputStream("pages/" + i + "file.html");

				// Creamos el objeto de entrada
				entrada = new DataInputStream(fstream);
				// Creamos el Buffer de Lectura
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(entrada));
				String strLinea;boolean isFirst=true;
				// Leer el archivo linea por linea
				while ((strLinea = buffer.readLine()) != null) {
					// Imprimimos la l�nea por pantalla
					if (strLinea.contains("class=\"Res_ResultSeco\"")) {
						String premioMayor = strLinea.trim();
						strLinea = buffer.readLine();
						if (strLinea.contains("class=\"Res_ResultSeco\"")) {
							premioMayor = premioMayor.substring(64, 68);
							premioMayor = premioMayor.trim();

							strLinea = strLinea.trim();
							serie = strLinea.substring(65, 68);
							serie = serie.trim();
							numeros.add(serie);
							int p1 = Integer.parseInt(""
									+ premioMayor.charAt(0));
							int p2 = Integer.parseInt(""
									+ premioMayor.charAt(1));
							int p3 = Integer.parseInt(""
									+ premioMayor.charAt(2));
							int p4 = Integer.parseInt(""
									+ premioMayor.charAt(3));

							int ps1 = Integer.parseInt("" + serie.charAt(0));
							int ps2 = Integer.parseInt("" + serie.charAt(1));
							int ps3 = Integer.parseInt("" + serie.charAt(2));
							
							if(isFirst&&mayor){
								System.out.println("Premio Mayor:"+p1+p2+p3+p4+" "+ps1+ps2+ps3);
								isFirst=false;
							}
							
							posicion1[p1]++;
							posicion2[p2]++;
							posicion3[p3]++;
							posicion4[p4]++;

							posicionserie1[ps1]++;
							posicionserie2[ps2]++;
							posicionserie3[ps3]++;
							// System.out.println(premioMayor);
							// System.out.println(serie);
							if(mayor)break;
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int mayor1=posicion1[0];
		int index1=0;
		int mayor2=posicion2[0];
		int index2=0;
		int mayor3=posicion3[0];
		int index3=0;
		int mayor4=posicion4[0];
		int index4=0;
		int mayor5=posicionserie1[0];
		int index5=0;
		int mayor6=posicionserie2[0];
		int index6=0;
		int mayor7=posicionserie3[0];
		int index7=0;
		
		System.out.println("Primera posición");
		String sp1 = "1era posición", sp2 = "2da posicion", sp3 = "3ra posicion ", sp4 = "4ta posicion", sp5 = "Primera posicion serie", sp6 = "2da posicion serie", sp7 = "3era posicion serie";
		for (int j = 0; j < posicion1.length; j++) {
			
			if(posicion1[j]>mayor1){
				index1=j;
				mayor1=posicion1[j];
			} if(posicion2[j]>mayor2){
				index2=j;
				mayor2=posicion2[j];
			} if(posicion3[j]>mayor3){
				index3=j;
				mayor3=posicion3[j];
			} if(posicion4[j]>mayor4){
				index4=j;
				mayor4=posicion4[j];
			} if(posicionserie1[j]>mayor5){
				index5=j;
				mayor5=posicionserie1[j];
			} if(posicionserie2[j]>mayor6){
				index6=j;
				mayor6=posicionserie2[j];
			} if(posicionserie3[j]>mayor7){
				index7=j;
				mayor7=posicionserie3[j];
			}
		
			sp1 += "\n" + posicion1[j] + " veces Aparece:" + j;
			sp2 += "\n" + posicion2[j] + " veces Aparece:" + j;
			sp3 += "\n" + posicion3[j] + " veces Aparece:" + j;
			sp4 += "\n" + posicion4[j] + " veces Aparece:" + j;

			sp5 += "\n" + posicionserie1[j] + " veces Aparece:" + j;
			sp6 += "\n" + posicionserie2[j] + " veces Aparece:" + j;
			sp7 += "\n" + posicionserie3[j] + " veces Aparece:" + j;
		}

		System.out.println(sp1);
		System.out.println();
		System.out.println(sp2);
		System.out.println();
		System.out.println(sp3);
		System.out.println();
		System.out.println(sp4);
		System.out.println();
		System.out.println(sp5);
		System.out.println();
		System.out.println(sp6);
		System.out.println();
		System.out.println(sp7);
		
		System.out.println("The mayor in the index 1 is:"+index1+" with value: "+mayor1);
		System.out.println("The mayor in the index 2 is:"+index2+" with value: "+mayor2);
		System.out.println("The mayor in the index 3 is:"+index3+" with value: "+mayor3);
		System.out.println("The mayor in the index 4 is:"+index4+" with value: "+mayor4);
		System.out.println("The mayor in the index serie 1 is:"+index5+" with value: "+mayor5);
		System.out.println("The mayor in the index serie 2 is:"+index6+" with value: "+mayor6);
		System.out.println("The mayor in the index serie 3 is:"+index7+" with value: "+mayor7);
	}

	private void saveFile(String sorteo) {

		//
		// http://www.loteriademanizales.com/web2014/resultados.php?sorteo=4509&button=Consultar
		URL url;
		try {
			url = new URL(
					"http://www.loteriademanizales.com/web2014/resultados.php?sorteo="
							+ sorteo + "&button=Consultar");

			URLConnection urlconn = url.openConnection();
			inputS = urlconn.getInputStream();
			fos = new FileOutputStream("pages/" + sorteo + "file.html");
			byte bytes[] = new byte[200000];
			int a = inputS.read(bytes);
			while (a > 0) {
				fos.write(bytes, 0, a);
				a = inputS.read(bytes);
			}
			String s = new String(bytes);
			System.out.println("save sorteo :" + sorteo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
