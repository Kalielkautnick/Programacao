
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

/**
 *
 * @author marcel
 */
public class Apresentacao extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private Camara camara = new Camara();
	private int IDProjeto = 1;
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * Creates new form Apresentacao
	 */
	public Apresentacao() {
		initComponents();
	}

	private void initComponents() {

		jLabel7 = new javax.swing.JLabel();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jPanel1.setBackground(SystemColor.activeCaption);
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setFont(new Font("Tahoma", Font.BOLD, 12));
		p_numeroPartido = new javax.swing.JTextField();
		p_nomePartido = new javax.swing.JTextField();
		p_cadastrarPartido = new javax.swing.JButton();
		p_cadastrarPartido.setBackground(Color.GREEN);
		p_cadastrarPartido.setFont(new Font("Tahoma", Font.BOLD, 13));
		jPanel2 = new javax.swing.JPanel();
		jPanel2.setBackground(SystemColor.activeCaption);
		jLabel3 = new javax.swing.JLabel();
		jLabel3.setFont(new Font("Tahoma", Font.BOLD, 12));
		jLabel4 = new javax.swing.JLabel();
		jLabel4.setFont(new Font("Tahoma", Font.BOLD, 12));
		v_nomeVereador = new javax.swing.JTextField();
		v_numeroPartido = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		v_cadastrar = new javax.swing.JButton();
		v_cadastrar.setBackground(Color.GREEN);
		v_cadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));

		jLabel7.setText("jLabel7");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Câmara Vereadores");

		jLabel1.setText("Número:");

		jLabel2.setText("Nome:");

		p_cadastrarPartido.setText("Cadastrar");
		p_cadastrarPartido.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(p_cadastrarPartido, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
							.addContainerGap()
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(p_numeroPartido, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup()
									.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(p_nomePartido, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(174, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel1)
						.addComponent(p_numeroPartido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel2)
						.addComponent(p_nomePartido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(p_cadastrarPartido)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		jPanel1.setLayout(jPanel1Layout);

		jTabbedPane1.addTab("Partido", jPanel1);

		jLabel3.setText("Nome: ");

		jLabel4.setText("Número do partido:");

		v_cadastrar.setText("Cadastrar");
		v_cadastrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(
		    jPanel2Layout.createParallelGroup(Alignment.LEADING)
		        .addGroup(jPanel2Layout.createSequentialGroup()
		            .addContainerGap()
		            .addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
		                .addGroup(jPanel2Layout.createSequentialGroup()
		                    .addComponent(jLabel3)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(v_nomeVereador, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
		                .addGroup(jPanel2Layout.createSequentialGroup()
		                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(v_numeroPartido, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                .addGroup(jPanel2Layout.createSequentialGroup()
		                    .addGap(83)
		                    .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		            .addContainerGap(148, Short.MAX_VALUE))
		        .addGroup(jPanel2Layout.createSequentialGroup()
		            .addGap(169)
		            .addComponent(v_cadastrar, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
		            .addContainerGap(192, Short.MAX_VALUE))
		);
		jPanel2Layout.setVerticalGroup(
		    jPanel2Layout.createParallelGroup(Alignment.LEADING)
		        .addGroup(jPanel2Layout.createSequentialGroup()
		            .addGap(19)
		            .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
		                .addComponent(jLabel3)
		                .addComponent(v_nomeVereador, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addPreferredGap(ComponentPlacement.UNRELATED)
		            .addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
		                .addComponent(jLabel4)
		                .addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
		                .addComponent(v_numeroPartido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		            .addGap(28)
		            .addComponent(v_cadastrar)
		            .addGap(54)
		            .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		            .addGap(70))
		);
		jPanel2.setLayout(jPanel2Layout);

		jTabbedPane1.addTab("Vereador", jPanel2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jTabbedPane1).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(jTabbedPane1).addGap(28, 28, 28)));
		
		JPanel jPanel4 = new JPanel();
		jPanel4.setBackground(SystemColor.activeCaption);
		jTabbedPane1.addTab("Projeto de Lei", null, jPanel4, null);
		jPanel4.setLayout(null);
		
		pl_dtApresentacao = new JTextField();
		pl_dtApresentacao.setBounds(236, 95, 86, 23);
		pl_dtApresentacao.setColumns(10);
		jPanel4.add(pl_dtApresentacao);
		
		pl_titulo = new JTextField();
		pl_titulo.setBounds(90, 54, 244, 30);
		pl_titulo.setHorizontalAlignment(SwingConstants.LEFT);
		jPanel4.add(pl_titulo);
		pl_titulo.setColumns(10);
		
		pl_dtAprovacao = new JTextField();
		pl_dtAprovacao.setBounds(236, 129, 86, 23);
		jPanel4.add(pl_dtAprovacao);
		pl_dtAprovacao.setColumns(10);
		
		pl_numero = new JTextField();
		pl_numero.setHorizontalAlignment(SwingConstants.CENTER);
		pl_numero.setEnabled(false);
		pl_numero.setFont(new Font("Tahoma", Font.BOLD, 18));
		pl_numero.setColumns(10);
		pl_numero.setBounds(409, 95, 54, 39);
		pl_numero.setText(Integer.toString(IDProjeto));
		jPanel4.add(pl_numero);
		
		JLabel lblNewLabel = new JLabel("Titulo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 60, 77, 14);
		jPanel4.add(lblNewLabel);
		
		JLabel lblDataApresentacaoddmmaaaa = new JLabel("Data Apresentacao (dd/mm/aaaa):");
		lblDataApresentacaoddmmaaaa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataApresentacaoddmmaaaa.setBounds(10, 98, 216, 14);
		jPanel4.add(lblDataApresentacaoddmmaaaa);
		
		JLabel lblDataAprovacaoddmmaaaa = new JLabel("Data Aprovacao (dd/mm/aaaa):");
		lblDataAprovacaoddmmaaaa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDataAprovacaoddmmaaaa.setBounds(10, 132, 216, 14);
		jPanel4.add(lblDataAprovacaoddmmaaaa);
		
		JLabel lblNProjeto = new JLabel("N° Projeto");
		lblNProjeto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNProjeto.setBounds(398, 70, 78, 14);
		jPanel4.add(lblNProjeto);
		
		JButton pl_btnAdicionar = new JButton("Adicionar");
		pl_btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				LocalDate dt_ap;
				LocalDate dt_apr;
				try {
					dt_ap = (pl_dtAprovacao.getText().isBlank()) ? null : LocalDate.parse(pl_dtAprovacao.getText(), fmt);
					dt_apr = LocalDate.parse(pl_dtApresentacao.getText(), fmt);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Formato de Data inválido");
					return;
				}
				
				try {
					
					ProjetoDeLei pl = new ProjetoDeLei(camara.gerarNumeroProjeto(), 
							pl_titulo.getText(), dt_apr, dt_ap);
					
					camara.addProjeto(pl_nPartido.getText(), pl_nomeVereador.getText(), pl);
					
					JOptionPane.showMessageDialog(null, "Projeto Cadastrao com sucesso");
					plc_numeroProjeto.setText(Integer.toString(camara.getContProjetos()));
					pl_numero.setText(Integer.toString(camara.getContProjetos()));
					
				} catch (NullPointerException en) {
					camara.cancelarNumeroProjeto();
					JOptionPane.showMessageDialog(null, en.getMessage());
				} catch (IllegalArgumentException il) {
					camara.cancelarNumeroProjeto();
					JOptionPane.showMessageDialog(null, il.getMessage());
				} 
				
			}
		});
		pl_btnAdicionar.setBackground(new Color(255, 0, 0));
		pl_btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 15));
		pl_btnAdicionar.setBounds(286, 199, 161, 39);
		jPanel4.add(pl_btnAdicionar);
		
		JLabel lbl_nomeVereador = new JLabel("Nome vereador: ");
		lbl_nomeVereador.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_nomeVereador.setBounds(10, 14, 130, 14);
		jPanel4.add(lbl_nomeVereador);
		
		pl_nomeVereador = new JTextField();
		pl_nomeVereador.setHorizontalAlignment(SwingConstants.LEFT);
		pl_nomeVereador.setColumns(10);
		pl_nomeVereador.setBounds(139, 6, 191, 30);
		jPanel4.add(pl_nomeVereador);
		
		pl_nPartido = new JTextField();
		pl_nPartido.setHorizontalAlignment(SwingConstants.CENTER);
		pl_nPartido.setFont(new Font("Tahoma", Font.BOLD, 14));
		pl_nPartido.setColumns(10);
		pl_nPartido.setBounds(433, 7, 54, 30);
		jPanel4.add(pl_nPartido);
		
		lblNPartido = new JLabel("N° Partido:");
		lblNPartido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNPartido.setBounds(340, 16, 107, 14);
		jPanel4.add(lblNPartido);
		
		JPanel jPanel5 = new JPanel();
		jPanel5.setBackground(SystemColor.activeCaption);
		jTabbedPane1.addTab("Projeto de Lei Complemetar", null, jPanel5, null);
		jPanel5.setLayout(null);
		
		plc_titulo = new JTextField();
		plc_titulo.setBounds(68, 74, 279, 27);
		jPanel5.add(plc_titulo);
		plc_titulo.setColumns(10);
		
		plc_dtApresentacao = new JTextField();
		plc_dtApresentacao.setColumns(10);
		plc_dtApresentacao.setBounds(245, 113, 114, 27);
		jPanel5.add(plc_dtApresentacao);
		
		plc_dtAprovacao = new JTextField();
		plc_dtAprovacao.setColumns(10);
		plc_dtAprovacao.setBounds(235, 143, 124, 27);
		jPanel5.add(plc_dtAprovacao);
		
		plc_artigoLO = new JTextField();
		plc_artigoLO.setColumns(10);
		plc_artigoLO.setBounds(173, 173, 198, 27);
		jPanel5.add(plc_artigoLO);
		
		plc_votosFavoraveis = new JTextField();
		plc_votosFavoraveis.setColumns(10);
		plc_votosFavoraveis.setBounds(149, 211, 100, 27);
		jPanel5.add(plc_votosFavoraveis);
		
		plc_numeroProjeto = new JTextField();
		plc_numeroProjeto.setFont(new Font("Tahoma", Font.BOLD, 18));
		plc_numeroProjeto.setEnabled(false);
		plc_numeroProjeto.setHorizontalAlignment(SwingConstants.CENTER);
		plc_numeroProjeto.setColumns(10);
		plc_numeroProjeto.setText(Integer.toString(IDProjeto));
		plc_numeroProjeto.setBounds(409, 79, 53, 40);
		jPanel5.add(plc_numeroProjeto);
		
		plc_btnAdicionar = new JButton("Adicionar");
		plc_btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LocalDate dt_ap;
				LocalDate dt_apr;
				try {
					dt_ap = (plc_dtAprovacao.getText().isBlank()) ? null : LocalDate.parse(plc_dtAprovacao.getText(), fmt);
					dt_apr = LocalDate.parse(plc_dtApresentacao.getText(), fmt);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Formato de Data inválido");
					return;
				}
				
				try {
					
					ProjetoDeLeiComplementar plc = new ProjetoDeLeiComplementar(camara.gerarNumeroProjeto(), 
							plc_titulo.getText(), dt_apr, dt_ap, plc_artigoLO.getText() , 
							Integer.parseInt(plc_votosFavoraveis.getText()) 
							);
					
					camara.addProjeto(pl_nPartido.getText(), pl_nomeVereador.getText(), plc);
					JOptionPane.showMessageDialog(null, "Projeto Cadastrao com sucesso");
					
					pl_numero.setText(Integer.toString(camara.getContProjetos()));
					plc_numeroProjeto.setText(Integer.toString(camara.getContProjetos()));
					
				} catch (NullPointerException en) {
					camara.cancelarNumeroProjeto();
					JOptionPane.showMessageDialog(null, en.getMessage());
				} catch (IllegalArgumentException il) {
					camara.cancelarNumeroProjeto();
					JOptionPane.showMessageDialog(null, il.getMessage());
				} 	
				
				
				
				
			}
		});
		plc_btnAdicionar.setFont(new Font("Tahoma", Font.BOLD, 15));
		plc_btnAdicionar.setBackground(new Color(255, 140, 0));
		plc_btnAdicionar.setBounds(362, 228, 114, 40);
		jPanel5.add(plc_btnAdicionar);
		
		lblNewLabel_1 = new JLabel("Titulo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 79, 53, 14);
		jPanel5.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Data apresentacao (dd/mm/aaaa):");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 118, 239, 14);
		jPanel5.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Data aprovacao (dd/mm/aaaa):");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 148, 227, 14);
		jPanel5.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Artigo de Lei Orgânica:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 176, 153, 17);
		jPanel5.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Votos favoraveis:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(10, 216, 153, 14);
		jPanel5.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("N° Projeto");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(399, 63, 77, 14);
		jPanel5.add(lblNewLabel_6);
		
		plc_nomeVereador = new JTextField();
		plc_nomeVereador.setColumns(10);
		plc_nomeVereador.setBounds(122, 12, 198, 27);
		jPanel5.add(plc_nomeVereador);
		
		lblNewLabel_7 = new JLabel("Nome vereador:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(10, 17, 129, 14);
		jPanel5.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("N° Partido:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_8.setBounds(330, 18, 82, 14);
		jPanel5.add(lblNewLabel_8);
		
		plc_nPartido = new JTextField();
		plc_nPartido.setColumns(10);
		plc_nPartido.setBounds(410, 12, 65, 27);
		jPanel5.add(plc_nPartido);
		jPanel3 = new javax.swing.JPanel();
		jPanel3.setBackground(SystemColor.controlHighlight);
		jButton3 = new javax.swing.JButton();
		jButton3.setBackground(Color.RED);
		jButton3.setFont(new Font("Tahoma", Font.BOLD, 13));
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vereador v = camara.getVereadorMenorDesempenho();
				String str;
				if (v == null) {
					str = "Não há vereadores cadastrados";
				} else {
					str = "Menor desempenho\n\n" + v.getNome() + " do partido " + v.getPartido().getNome() + " = "
							+ v.getDesempenho();
				}
				JOptionPane.showMessageDialog(jTabbedPane1, str);
			}
		});
		jButton4 = new javax.swing.JButton();
		jButton4.setBackground(Color.GREEN);
		jButton4.setForeground(Color.BLACK);
		jButton4.setFont(new Font("Tahoma", Font.BOLD, 13));
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vereador v = camara.getVereadorMaisProjAprov();
				String str;
				if (v == null) {
					str = "Não há vereadores cadastrados";
				} else {
					str = "Mais projetos cadastrados\n\n" + v.getNome() + " do partido " + v.getPartido().getNome()
							+ " = " + v.getDesempenho();
				}
				JOptionPane.showMessageDialog(jTabbedPane1, str);
			}
		});
		jLabel9 = new javax.swing.JLabel();
		jLabel9.setFont(new Font("Tahoma", Font.BOLD, 11));
		jTextField7 = new javax.swing.JTextField();
		jButton5 = new javax.swing.JButton();
		jButton5.setBackground(Color.LIGHT_GRAY);
		jButton5.setFont(new Font("Tahoma", Font.BOLD, 11));
		jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Partido p = camara.getPartido(jTextField7.getText());
					String str;
					if (p == null) {
						str = "Não há partido com este número";
					} else {
						str = "Partido " + p.getNome() + "\n\nTotal de projetos aprovados = " + p.getTotalProjetosAprovados()
								+ "\nTotal de projetos apresentados = " + p.getTotalProjetosApresentados()
								+ "\nMédia de desempenho do partido = " + p.getMediaDesempenho();
					}
					JOptionPane.showMessageDialog(jTabbedPane1, str);
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(jTabbedPane1, "Digite um n�mero de partido v�lido");
				}
			}
		});
		jSeparator1 = new javax.swing.JSeparator();
		jButton6 = new javax.swing.JButton();
		jButton6.setBackground(Color.BLUE);
		jButton6.setFont(new Font("Tahoma", Font.BOLD, 13));
		jButton7 = new javax.swing.JButton();
		jButton7.setBackground(Color.YELLOW);
		jButton7.setFont(new Font("Tahoma", Font.BOLD, 13));
		
				jButton3.setText("Vereador menor desempenho");
				
						jButton4.setText("Vereador com mais proj. aprovados");
						
								jLabel9.setText("Número do partido:");
								
										jButton5.setText("Estatísticas do partido");
										
												jButton6.setText("Total de projetos da Câmara");
												jButton6.addActionListener(new java.awt.event.ActionListener() {
													public void actionPerformed(java.awt.event.ActionEvent evt) {
														jButton6ActionPerformed(evt);
													}
												});
												
														jButton7.setText("Vereadores acima da média");
														jButton7.addActionListener(new java.awt.event.ActionListener() {
															public void actionPerformed(java.awt.event.ActionEvent evt) {
																jButton7ActionPerformed(evt);
															}
														});
																
																JButton btn_mostrar_proj = new JButton();
																btn_mostrar_proj.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		String msg = camara.mostra();
																		
																		if(msg == null || msg.isBlank()) {
																			JOptionPane.showMessageDialog(null, "Sem projetos Cadastrados");
																		} else {
																			JOptionPane.showMessageDialog(null, msg);
																		}
																	}
																});
																btn_mostrar_proj.setText("Mostrar Projetos");
																btn_mostrar_proj.setFont(new Font("Tahoma", Font.BOLD, 13));
																btn_mostrar_proj.setBackground(new Color(0, 128, 128));
														
																javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
																jPanel3Layout.setHorizontalGroup(
																	jPanel3Layout.createParallelGroup(Alignment.LEADING)
																		.addGroup(jPanel3Layout.createSequentialGroup()
																			.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
																				.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING, false)
																					.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
																					.addComponent(jButton4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																					.addComponent(jButton6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																					.addComponent(jButton7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																				.addGroup(jPanel3Layout.createSequentialGroup()
																					.addContainerGap()
																					.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
																						.addGroup(jPanel3Layout.createSequentialGroup()
																							.addComponent(jLabel9)
																							.addPreferredGap(ComponentPlacement.RELATED)
																							.addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
																							.addGap(18)
																							.addComponent(jButton5))
																						.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																				.addComponent(btn_mostrar_proj, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE))
																			.addContainerGap(81, Short.MAX_VALUE))
																);
																jPanel3Layout.setVerticalGroup(
																	jPanel3Layout.createParallelGroup(Alignment.LEADING)
																		.addGroup(jPanel3Layout.createSequentialGroup()
																			.addContainerGap()
																			.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE)
																				.addComponent(jLabel9)
																				.addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(jButton5))
																			.addGap(13)
																			.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(ComponentPlacement.UNRELATED)
																			.addComponent(jButton3)
																			.addGap(18)
																			.addComponent(jButton4)
																			.addGap(18)
																			.addComponent(jButton6)
																			.addGap(18)
																			.addComponent(jButton7)
																			.addPreferredGap(ComponentPlacement.UNRELATED)
																			.addComponent(btn_mostrar_proj, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
																			.addContainerGap(22, Short.MAX_VALUE))
																);
																jPanel3.setLayout(jPanel3Layout);
																
																		jTabbedPane1.addTab("Consultas", jPanel3);

		pack();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		String str;
		try {
			if (camara.getPartido(p_numeroPartido.getText()) == null) {
				Partido partido = new Partido();
				partido.setNome(p_nomePartido.getText());
				partido.setNumero(p_numeroPartido.getText());
				camara.addPartido(partido);
				str = "Partido cadastrado";
			} else {
				str = "Já há partido com este número. Escolha outro número";
			}
		} catch (IllegalArgumentException iae) {
			str = iae.getMessage();
		}
		JOptionPane.showMessageDialog(this, str);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
			try {
				Partido p = null;
				if (camara.getQtdPartidos() > 0) {
				if (v_numeroPartido.getText() != null && v_nomeVereador.getText() != null) {
				p = camara.getPartido(v_numeroPartido.getText());
				jLabel8.setText(p.getNome());
				Vereador ver = new Vereador(v_nomeVereador.getText());
				
				camara.addVereador(ver,p);
				JOptionPane.showMessageDialog(this, "Vereador cadastrado com desempenho " + ver.getDesempenho());
				} else {
					JOptionPane.showMessageDialog(this, "Faltam Informações a serem preenchidas");
					}
				} else {
					JOptionPane.showMessageDialog(this, "Não existe partidos cadastrados, o vereador precisa estar vinculado a um partido.");
				}
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}		
			catch (NullPointerException ex) {
				JOptionPane.showMessageDialog(this, "Numero de partido não encontrado");
			}
	}

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
		String str = "Total de projetos apresentados: " + camara.getTotalProjApres() + "\nTotal de projetos aprovados: " + camara.getTotalProjAprov();
		JOptionPane.showMessageDialog(this, str);
	}

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
		List<Vereador> acima = camara.getVereadoresAcimaMedia();
		String str = "Vereadores acima da média: " + camara.getDesempenhoMedioCamara() + "\n";
		for (Vereador v : acima) {
			str += "\n" + v.getNome() + " do partido " + v.getPartido().getNome() + " : " + v.getDesempenho();
		}
		JOptionPane.showMessageDialog(this, str);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Apresentacao().setVisible(true);
			}
		});
	}

	// Variables declaration
	private javax.swing.JButton p_cadastrarPartido;
	private javax.swing.JButton v_cadastrar;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextField p_numeroPartido;
	private javax.swing.JTextField p_nomePartido;
	private javax.swing.JTextField v_nomeVereador;
	private javax.swing.JTextField v_numeroPartido;
	private javax.swing.JTextField jTextField7;
	private JTextField pl_dtAprovacao;
	private JTextField pl_titulo;
	private JTextField pl_dtApresentacao;
	private JTextField pl_numero;
	private JTextField plc_titulo;
	private JTextField plc_dtApresentacao;
	private JTextField plc_dtAprovacao;
	private JTextField plc_artigoLO;
	private JTextField plc_votosFavoraveis;
	private JButton plc_btnAdicionar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField plc_numeroProjeto;
	private JLabel lblNewLabel_6;
	private JTextField pl_nomeVereador;
	private JTextField pl_nPartido;
	private JLabel lblNPartido;
	private JTextField plc_nomeVereador;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JTextField plc_nPartido;
}