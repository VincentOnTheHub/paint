package tp_01;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Dimension;
import javax.swing.SwingConstants;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panelCouleurs;
	private JToggleButton tglbtn_Crayon;
	private JToggleButton tglbtn_Save;
	private JToggleButton tglbtn_Efface;
	private JToggleButton tglbtn_Pipette;
	private JToggleButton tglbtn_PotPeinture;
	private JToggleButton tglbtnNewToggleButton_5;
	private JToggleButton tglbtn_Rectangle;
	private JToggleButton tglbtn_Triangle;
	private JToggleButton tglbtn_Cercle;
	private JToggleButton tglbtn_couleur2;
	private JToggleButton tglbtn_couleur1;
	private JLabel lblNew_Rose;
	private JLabel lblNew_Jaune;
	private JLabel lblNew_Bleue;
	private JLabel lblNew_Rouge;
	private JLabel lblNew_Orange;
	private JLabel lblNew_Magenta;
	private JLabel lblNew_Vert;
	private JLabel lblNew_Noir;
	private JLabel lblNew_Cyan;
	private JLabel lblNew_Blanc;
	private JLabel lblNewLabel;
	private JLabel BoutCouleur1;
	private JLabel BoutCouleur2;
	private JTextField textField_taille;
	private SurfaceDessin surface;
	private Color couleurChoisie1;
	private Color couleurChoisie2;
	private Color couleurTemp;
	private Color surfaceDessin1;
	private Point p;
	private int x;
	private int y;
	private ButtonGroup groupeOutils;
	private ButtonGroup groupePanelCouleur;
	
	//Init formes
	private Forme forme;
	private Crayon crayon;
	private Rectangle rectangle;
	private Cercle cercle;
	private Efface efface;
	private Triangle triangle;
	//Init pour export image
	private BufferedImage imageBuff ;
	//Étape 1
	private Ecouteur ec;
	private EcouteurSouris es;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setLocationRelativeTo(null);//Centre la fenêtre au lancement
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("Paint - TP1 - Vincent Vinh");
		setBounds(100, 100, 1114, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		couleurChoisie1 = Color.BLACK;
		couleurChoisie2 = Color.BLACK;
		couleurTemp = Color.BLACK;
		surfaceDessin1 = Color.WHITE;
		forme = new Forme();
		p = new Point();
		crayon = new Crayon();
		
		
		//Pour ajouter une icone au ToggleBut.
		groupeOutils = new ButtonGroup();
		groupePanelCouleur = new ButtonGroup();
		
		
		panel = new JPanel();
		panel.setBounds(10, 11, 364, 100);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tglbtn_Crayon = new JToggleButton("");
		tglbtn_Crayon.setPreferredSize(new Dimension(50, 40));
		tglbtn_Crayon.setIcon(new ImageIcon("icones/crayon.gif"));
		panel.add(tglbtn_Crayon);
		
		tglbtn_Efface = new JToggleButton("");
		tglbtn_Efface.setPreferredSize(new Dimension(50, 40));
		tglbtn_Efface.setIcon(new ImageIcon("icones/efface.gif"));
		panel.add(tglbtn_Efface);
		
		tglbtn_Pipette = new JToggleButton("");
		tglbtn_Pipette.setPreferredSize(new Dimension(50, 40));
		tglbtn_Pipette.setIcon(new ImageIcon("icones/pipette.gif"));
		panel.add(tglbtn_Pipette);
		
		tglbtn_PotPeinture = new JToggleButton("");
		tglbtn_PotPeinture.setPreferredSize(new Dimension(50, 40));
		tglbtn_PotPeinture.setIcon(new ImageIcon("icones/remplissage.gif"));
		panel.add(tglbtn_PotPeinture);
		
		tglbtnNewToggleButton_5 = new JToggleButton("");
		tglbtnNewToggleButton_5.setPreferredSize(new Dimension(50, 40));
		tglbtnNewToggleButton_5.setBorderPainted(false);
		tglbtnNewToggleButton_5.setEnabled(false);
		panel.add(tglbtnNewToggleButton_5);
		
		tglbtn_Save = new JToggleButton("");
		tglbtn_Save.setPreferredSize(new Dimension(50, 40));
		tglbtn_Save.setIcon(new ImageIcon("icones/save.gif"));
		panel.add(tglbtn_Save);
		
		panel_1 = new JPanel();
		panel_1.setBounds(384, 11, 184, 100);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 40));
		
		tglbtn_Rectangle = new JToggleButton("");
		tglbtn_Rectangle.setIcon(new ImageIcon("icones/rectangle.gif"));
		tglbtn_Rectangle.setPreferredSize(new Dimension(40, 40));
		panel_1.add(tglbtn_Rectangle);
		
		tglbtn_Cercle = new JToggleButton("");
		tglbtn_Cercle.setIcon(new ImageIcon("icones/cercle.gif"));
		tglbtn_Cercle.setPreferredSize(new Dimension(40, 40));
		panel_1.add(tglbtn_Cercle);
		
		tglbtn_Triangle = new JToggleButton("");
		tglbtn_Triangle.setIcon(new ImageIcon("icones/triangle.gif"));
		tglbtn_Triangle.setPreferredSize(new Dimension(40, 40));
		panel_1.add(tglbtn_Triangle);
		
		textField_taille = new JTextField();
		textField_taille.setText("11");
		textField_taille.setPreferredSize(new Dimension(4, 20));
		panel.add(textField_taille);
		textField_taille.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBounds(818, 11, 270, 100);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(2, 5, 3, 3));
		
		lblNew_Rose = new JLabel("");
		lblNew_Rose.setOpaque(true);
		lblNew_Rose.setBackground(Color.PINK);
		panel_2.add(lblNew_Rose);
		
		lblNew_Jaune = new JLabel("");
		lblNew_Jaune.setBackground(Color.YELLOW);
		lblNew_Jaune.setOpaque(true);
		panel_2.add(lblNew_Jaune);
		
		lblNew_Magenta = new JLabel("");
		lblNew_Magenta.setBackground(Color.MAGENTA);
		lblNew_Magenta.setOpaque(true);
		panel_2.add(lblNew_Magenta);
		
		lblNew_Bleue = new JLabel("");
		lblNew_Bleue.setBackground(Color.BLUE);
		lblNew_Bleue.setOpaque(true);
		panel_2.add(lblNew_Bleue);
		
		lblNew_Rouge = new JLabel("");
		lblNew_Rouge.setBackground(Color.RED);
		lblNew_Rouge.setOpaque(true);
		panel_2.add(lblNew_Rouge);
		
		lblNew_Orange = new JLabel("");
		lblNew_Orange.setBackground(Color.ORANGE);
		lblNew_Orange.setOpaque(true);
		panel_2.add(lblNew_Orange);
		
		lblNew_Vert = new JLabel("");
		lblNew_Vert.setBackground(Color.GREEN);
		lblNew_Vert.setOpaque(true);
		panel_2.add(lblNew_Vert);
		
		lblNew_Cyan = new JLabel("");
		lblNew_Cyan.setBackground(Color.CYAN);
		lblNew_Cyan.setOpaque(true);
		panel_2.add(lblNew_Cyan);
		
		lblNew_Noir = new JLabel("");
		lblNew_Noir.setBackground(Color.BLACK);
		lblNew_Noir.setOpaque(true);
		panel_2.add(lblNew_Noir);
		
		lblNew_Blanc = new JLabel("");
		lblNew_Blanc.setBackground(Color.WHITE);
		lblNew_Blanc.setOpaque(true);
		panel_2.add(lblNew_Blanc);
		
		panelCouleurs = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCouleurs.getLayout();
		flowLayout.setVgap(20);
		panelCouleurs.setBounds(578, 11, 230, 100);
		getContentPane().add(panelCouleurs);
		
		BoutCouleur1 = new JLabel("");
		BoutCouleur1.setPreferredSize(new Dimension(55,30));
		BoutCouleur1.setOpaque(true);
		BoutCouleur1.setBackground(couleurChoisie1);
		
		BoutCouleur2 = new JLabel("");
		BoutCouleur2.setOpaque(true);
		BoutCouleur2.setBackground(couleurChoisie2);
		BoutCouleur2.setPreferredSize(new Dimension(55,30));
		
		tglbtn_couleur1 = new JToggleButton("Couleur 1");
		tglbtn_couleur1.setPreferredSize(new Dimension(100, 60));
		tglbtn_couleur1.setMargin(new Insets(2, 2, 2, 2));
		tglbtn_couleur1.setVerticalAlignment(SwingConstants.BOTTOM);
		tglbtn_couleur1.setLayout(new FlowLayout());
		panelCouleurs.add(tglbtn_couleur1);
		tglbtn_couleur1.add(BoutCouleur1);
		
		tglbtn_couleur2 = new JToggleButton("Couleur 2");
		tglbtn_couleur2.setPreferredSize(new Dimension(100, 60));
		tglbtn_couleur2.setMargin(new Insets(2, 2, 2, 2));
		tglbtn_couleur2.setVerticalAlignment(SwingConstants.BOTTOM);
		tglbtn_couleur2.setLayout(new FlowLayout());
		panelCouleurs.add(tglbtn_couleur2);
		tglbtn_couleur2.add(BoutCouleur2);
		
		lblNewLabel = new JLabel("Taille du texte :       ");
		panel.add(lblNewLabel);
		
		surface = new SurfaceDessin();
		surface.setLocation(10, 150);
		surface.setSize(1075, 550);
		this.getContentPane().add(surface);		
		
		//Ajout des boutons outils dans un groupe
		groupeOutils.add(tglbtn_Crayon);
		groupeOutils.add(tglbtn_Efface);
		groupeOutils.add(tglbtn_Pipette);
		groupeOutils.add(tglbtn_PotPeinture);
		groupeOutils.add(tglbtn_Save);//doute ???
		groupeOutils.add(tglbtn_Rectangle);
		groupeOutils.add(tglbtn_Cercle);
		groupeOutils.add(tglbtn_Triangle);
		groupePanelCouleur.add(tglbtn_couleur1);
		groupePanelCouleur.add(tglbtn_couleur2);
		
		tglbtn_couleur1.setSelected(true);
		tglbtn_Crayon.setSelected(true);
		
		//Étape 2
		ec = new Ecouteur();
		es = new EcouteurSouris();		
		surface.addMouseListener(es);
		surface.addMouseMotionListener(es);
		lblNew_Rose.addMouseListener(es);
		lblNew_Blanc.addMouseListener(es);
		lblNew_Rouge.addMouseListener(es);
		lblNew_Cyan.addMouseListener(es);
		lblNew_Bleue.addMouseListener(es);
		lblNew_Jaune.addMouseListener(es);
		lblNew_Noir.addMouseListener(es);
		lblNew_Vert.addMouseListener(es);
		lblNew_Orange.addMouseListener(es);
		lblNew_Magenta.addMouseListener(es);
		
		tglbtn_Save.addActionListener(ec);
		tglbtn_couleur1.addActionListener(ec);
		tglbtn_couleur2.addActionListener(ec);
	}
	private class SurfaceDessin extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void paintComponent( Graphics g )
		{
			super.paintComponent(g);
			//Anti alias
			Graphics2D g2 = (Graphics2D)  g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			setBackground(surfaceDessin1);//couleur de fond - Blanc au démarrage
			Stroke tempStroke = new BasicStroke();
			int size; 
			
			//******************dessine les formes du vecteur de forme***********************//
			
			if(!forme.getForme().isEmpty())//minimum de 2 points
			{		
				for(int i = 0 ; i < forme.getForme().size()  ; i++)
				{
					int x1,y1,x2,y2;										
					g2.setColor(forme.getForme().get(i).getCouleur());
					if(forme.getForme().get(i).getTabPoints().size() > 1)// 2 points min requis
					{
						size = forme.getForme().get(i).getTaille();
						x1 = forme.getForme().get(i).getTabPoints().get(0).x;
						y1 = forme.getForme().get(i).getTabPoints().get(0).y;
						x2 = forme.getForme().get(i).getTabPoints().get(forme.getForme().get(i).getTabPoints().size()-1).x;
						y2 = forme.getForme().get(i).getTabPoints().get(forme.getForme().get(i).getTabPoints().size()-1).y ;
						if(forme.getForme().get(i).getFormeType() == 1 )//Si crayon
						{
							tempStroke = new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);//Arrondis
							g2.setStroke(tempStroke);
							for(int j = 0 ; j < forme.getForme().get(i).getTabPoints().size() -1 ;j++)
							{
								g.drawLine(forme.getForme().get(i).getTabPoints().get(j).x , forme.getForme().get(i).getTabPoints().get(j).y, forme.getForme().get(i).getTabPoints().get(j+1).x, forme.getForme().get(i).getTabPoints().get(j+1).y);								
							}	
						}
						else if(forme.getForme().get(i).getFormeType() == 2 )//Si rectangle
						{
							tempStroke = new BasicStroke(size,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER);
							g2.setStroke(tempStroke);
							g2.drawRect(x1, y1, x2 - x1, y2 - y1);
						
						}	
						else if(forme.getForme().get(i).getFormeType() == 3 )//Si cercle
						{
							tempStroke = new BasicStroke(size,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
							g2.setStroke(tempStroke);
							g2.drawOval(x1, y1, x2 - x1 , y2 - y1  );
						}
						else if(forme.getForme().get(i).getFormeType() == 4 )//Si efface
						{
							tempStroke = new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
							g2.setStroke(tempStroke);
							for(int j = 0 ; j < forme.getForme().get(i).getTabPoints().size() -1 ;j++)
							{
								g.drawLine(forme.getForme().get(i).getTabPoints().get(j).x , forme.getForme().get(i).getTabPoints().get(j).y, forme.getForme().get(i).getTabPoints().get(j+1).x, forme.getForme().get(i).getTabPoints().get(j+1).y);
							}	
						}
						else if(forme.getForme().get(i).getFormeType() == 5 )//Si triangle
						{
							tempStroke = new BasicStroke(size,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
							g2.setStroke(tempStroke);
							int[] xPoints={x1,x2,x2};
							int[] yPoints={y2,y1,y2};							
							g2.drawPolygon(xPoints, yPoints, 3);
						}
					}
				}
			}			
			
			//******************dessine la forme en cour***********************//			
			
			size = Integer.parseInt(textField_taille.getText());
			g2.setColor(couleurTemp);//Couleur en fonction du click gauche ou droite
			if(tglbtn_Crayon.isSelected()  && !crayon.getTabPoints().isEmpty() )
			{
				tempStroke = new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
				g2.setStroke(tempStroke);
				if(crayon.getTabPoints().size() > 1)//minimum de 2 points
				{
					for(int i = 0 ; i < crayon.getTabPoints().size()-1; i++)
					{		
						g.drawLine(crayon.getTabPoints().get(i).x, crayon.getTabPoints().get(i).y, crayon.getTabPoints().get(i+1).x, crayon.getTabPoints().get(i+1).y);	
					}
				}		
			}
			else if(tglbtn_Rectangle.isSelected() && !rectangle.getTabPoints().isEmpty())
			{
				g2.setColor(rectangle.getCouleur());
				tempStroke = new BasicStroke(size,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g2.setStroke(tempStroke);
				g2.drawRect( x, y, rectangle.getTabPoints().get(rectangle.getTabPoints().size()-1).x - x, rectangle.getTabPoints().get(rectangle.getTabPoints().size()-1).y - y);				
			}
			else if(tglbtn_Cercle.isSelected() && !cercle.getTabPoints().isEmpty())
			{
				tempStroke = new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
				g2.setStroke(tempStroke);
				g2.drawOval(cercle.getTabPoints().get(0).x, cercle.getTabPoints().get(0).y, cercle.getTabPoints().get(cercle.getTabPoints().size()-1).x - cercle.getTabPoints().get(0).x, cercle.getTabPoints().get(cercle.getTabPoints().size()-1).y - cercle.getTabPoints().get(0).y);
			}
			else if(tglbtn_Triangle.isSelected() && !triangle.getTabPoints().isEmpty())
			{
				tempStroke = new BasicStroke(size,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
				g2.setStroke(tempStroke);
				int[] xPoints={triangle.getTabPoints().get(0).x ,x,x};
				int[] yPoints={y,triangle.getTabPoints().get(0).y,y};
				g2.drawPolygon(xPoints, yPoints, 3);
			}
			else if(tglbtn_Efface.isSelected() && !efface.getTabPoints().isEmpty())
			{
				tempStroke = new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
				g2.setStroke(tempStroke);
				g2.setColor(surface.getBackground());
				if(efface.getTabPoints().size() > 1)//minimum de 2 points
				{
					for(int i = 0 ; i < efface.getTabPoints().size()-1; i++)
					{		
						g.drawLine(efface.getTabPoints().get(i).x, efface.getTabPoints().get(i).y, efface.getTabPoints().get(i+1).x, efface.getTabPoints().get(i+1).y);	
					}
				}		
			}
		}
	}
	//Étape 3
		private class Ecouteur implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				
				if( e.getSource().equals(tglbtn_Save))
				{					
					String nomFichier = "paint";
					 try {
							ImageIO.write(recupererImage(surface), "gif", new File(nomFichier + ".gif"));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					JOptionPane.showMessageDialog(null, "Sauvegarde réussie sous le nom de " + nomFichier +".gif");
				    tglbtn_Crayon.setSelected(true);//Resélectionne le crayon par defaut
				}
			}
		}
		private class EcouteurSouris implements MouseMotionListener , MouseListener
		{
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				p = new Point(e.getPoint());	
				if (tglbtn_Crayon.isSelected())
				{
					crayon.ajoutPoint(p,Integer.parseInt(textField_taille.getText()) , couleurTemp );
				}
				else if (tglbtn_Efface.isSelected())
				{
					efface.ajoutPoint(p,Integer.parseInt(textField_taille.getText()) , surface.getBackground() );
				}
				else if (tglbtn_Rectangle.isSelected())
				{				
					rectangle.ajoutPoint(p, Integer.parseInt(textField_taille.getText()), couleurTemp);
				}
				else if (tglbtn_Cercle.isSelected())
				{
					cercle.ajoutPoint(p, Integer.parseInt(textField_taille.getText()), couleurTemp);
				}
				else if (tglbtn_Triangle.isSelected())
				{
					//mise a jour des points X et Y pour le dragg
					x = e.getX();
					y = e.getY();
					triangle.ajoutPoint(p, Integer.parseInt(textField_taille.getText()), couleurTemp);
				}
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JLabel temp = null;				
				if(e.getSource() instanceof JLabel)
				{
					temp = (JLabel)e.getSource();
					if(tglbtn_couleur1.isSelected())
					{	
						BoutCouleur1.setBackground(temp.getBackground());
					}
					else if(tglbtn_couleur2.isSelected())
					{
						BoutCouleur2.setBackground(temp.getBackground());
					}
				}				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(tglbtn_Crayon.isSelected())
				{
					crayon = new Crayon();
				}
				else if(tglbtn_Efface.isSelected())
				{
					efface = new Efface();
				}
				else if(tglbtn_Rectangle.isSelected())
				{
					x = e.getX();
					y = e.getY();
					rectangle = new Rectangle();
				}
				else if(tglbtn_Cercle.isSelected())
				{
					cercle = new Cercle();
				}
				else if(tglbtn_Triangle.isSelected())
				{		
					triangle = new Triangle();
				}				
				else if(tglbtn_Pipette.isSelected())
				{
					//Coordonnées de la souris
					x = e.getX();
					y = e.getY();					
					imageBuff = recupererImage(surface);
					couleurTemp = new Color(imageBuff.getRGB(x, y));					
					if(tglbtn_couleur1.isSelected())
					{							
						couleurChoisie1 = couleurTemp;
						BoutCouleur1.setBackground(couleurChoisie1);
					}
					else if(tglbtn_couleur2.isSelected())
					{
						couleurChoisie2 = couleurTemp;
						BoutCouleur2.setBackground(couleurChoisie2);
					}
				}				
				else if(tglbtn_PotPeinture.isSelected())
				{
					if (e.getButton() == MouseEvent.BUTTON1 && !(e.getSource() instanceof JLabel) )
					{
						surfaceDessin1 = BoutCouleur1.getBackground();
					}
					else if(e.getButton() == MouseEvent.BUTTON3)
					{
						surfaceDessin1 = BoutCouleur2.getBackground();
					}
				}				
				if (e.getButton() == MouseEvent.BUTTON1)//click gauche
				{
					couleurTemp = BoutCouleur1.getBackground();
				}
				else if(e.getButton() == MouseEvent.BUTTON3)
				{
					couleurTemp = BoutCouleur2.getBackground();//click droit
				}
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(tglbtn_Crayon.isSelected())
				{
					forme.ajoutForme(crayon);
				}
				else if(tglbtn_Rectangle.isSelected())
				{
					forme.ajoutForme(rectangle);
				}
				else if(tglbtn_Cercle.isSelected())
				{
					forme.ajoutForme(cercle);
				}
				else if(tglbtn_Efface.isSelected())
				{
					forme.ajoutForme(efface);
				}
				else if(tglbtn_Triangle.isSelected())
				{
					forme.ajoutForme(triangle);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		}
		public BufferedImage recupererImage ( JPanel surface )
	  	{
	    	Dimension size = surface.getSize();
	    	BufferedImage image = new BufferedImage( size.width, 	size.height , BufferedImage.TYPE_INT_RGB);
	    	Graphics2D g2 = image.createGraphics();
	    	surface.paint(g2);
	    	return image;
	     }

}
