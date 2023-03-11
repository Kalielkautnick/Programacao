import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class Apresentacao {

	private JFrame frmSistemaDeTelefones;
	private JTextField r_tfNome;
	private JTextField r_tfNumero;
	private JTextField r_tfEndereco;
	private JTextField r_tfData_Instalacao;
	private JTextField c_tfNome;
	private JTextField c_tfNumero;
	private JTextField c_tfEndereco;
	private JTextField c_tfData_Instalacao;
	private JTextField e_tfNome;
	private JTextField e_tfNumero;
	private JTextField e_tfEndereco;
	private JTextField e_tfData_Instalacao;
	private JTextField e_tfOcorrencias;
	private JTextField p_tf_num_pesquisarc;
	private JTextField tf_ramo_atividade;
	private HashMap<String, Cliente> clientes = new HashMap<>();
	private JTextField tf_internet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apresentacao window = new Apresentacao();
					window.frmSistemaDeTelefones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Apresentacao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeTelefones = new JFrame();
		frmSistemaDeTelefones.getContentPane().setForeground(Color.BLACK);
		frmSistemaDeTelefones.setBackground(Color.BLUE);
		frmSistemaDeTelefones.setTitle("Sistema de Telefones");
		frmSistemaDeTelefones.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmSistemaDeTelefones.setBounds(100, 100, 535, 472);
		frmSistemaDeTelefones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeTelefones.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("CADASTRO");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(40, 39, 99, 27);
		frmSistemaDeTelefones.getContentPane().add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(40, 82, 440, 206);
		frmSistemaDeTelefones.getContentPane().add(tabbedPane);

		JPanel pResidencial = new JPanel();
		pResidencial.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE));
		pResidencial.setForeground(Color.BLUE);
		pResidencial.setBackground(Color.GRAY);
		tabbedPane.addTab("Residencial", null, pResidencial, null);
		pResidencial.setLayout(null);

		JLabel r_lblNome = new JLabel("Nome:");
		r_lblNome.setForeground(Color.BLACK);
		r_lblNome.setFont(new Font("Tahoma", Font.BOLD, 16));
		r_lblNome.setBounds(10, 11, 56, 13);
		pResidencial.add(r_lblNome);

		r_tfNome = new JTextField();
		r_tfNome.setBackground(new Color(220, 220, 220));
		r_tfNome.setForeground(Color.BLACK);
		r_tfNome.setSelectionColor(Color.BLUE);
		r_tfNome.setColumns(10);
		r_tfNome.setBounds(84, 10, 171, 19);
		pResidencial.add(r_tfNome);

		JLabel r_lblNumero = new JLabel("Numero:");
		r_lblNumero.setForeground(Color.BLACK);
		r_lblNumero.setFont(new Font("Tahoma", Font.BOLD, 16));
		r_lblNumero.setBounds(10, 35, 79, 13);
		pResidencial.add(r_lblNumero);

		r_tfNumero = new JTextField();
		r_tfNumero.setBackground(new Color(220, 220, 220));
		r_tfNumero.setForeground(Color.BLACK);
		r_tfNumero.setSelectionColor(Color.BLUE);
		r_tfNumero.setColumns(10);
		r_tfNumero.setBounds(94, 33, 161, 19);
		pResidencial.add(r_tfNumero);

		JLabel r_lblEndereco = new JLabel("Endereco:");
		r_lblEndereco.setForeground(Color.BLACK);
		r_lblEndereco.setFont(new Font("Tahoma", Font.BOLD, 16));
		r_lblEndereco.setBounds(10, 59, 96, 13);
		pResidencial.add(r_lblEndereco);

		r_tfEndereco = new JTextField();
		r_tfEndereco.setBackground(new Color(220, 220, 220));
		r_tfEndereco.setForeground(Color.BLACK);
		r_tfEndereco.setSelectionColor(Color.BLUE);
		r_tfEndereco.setColumns(10);
		r_tfEndereco.setBounds(104, 57, 304, 19);
		pResidencial.add(r_tfEndereco);

		JLabel r_lblData_Instalacao = new JLabel("Data Instalação: (dd/mm/yyyy)");
		r_lblData_Instalacao.setForeground(Color.BLACK);
		r_lblData_Instalacao.setFont(new Font("Tahoma", Font.BOLD, 16));
		r_lblData_Instalacao.setBounds(10, 83, 266, 17);
		pResidencial.add(r_lblData_Instalacao);

		r_tfData_Instalacao = new JTextField();
		r_tfData_Instalacao.setBackground(new Color(220, 220, 220));
		r_tfData_Instalacao.setForeground(Color.BLACK);
		r_tfData_Instalacao.setColumns(10);
		r_tfData_Instalacao.setBounds(286, 83, 122, 19);
		pResidencial.add(r_tfData_Instalacao);

		JButton r_btn = new JButton("Adicionar");
		r_btn.setForeground(Color.BLACK);
		r_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		r_btn.setBackground(Color.BLUE);
		r_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str;
				String internet;
				try {
					String aux = r_tfNumero.getText().replaceAll("[\\D]", "");
					Cliente c = new Cliente(r_tfNome.getText(), r_tfEndereco.getText());
					if (!clientes.containsKey(aux)) {
						clientes.put(aux, c);

						String ano = r_tfData_Instalacao.getText().replaceAll("[\\D]", "");
						Telefone t = new Telefone(r_tfNumero.getText(),
								LocalDate.of(Integer.parseInt(ano.substring(4, 8)),
										Integer.parseInt(ano.substring(2, 4)), Integer.parseInt(ano.substring(0, 2))),
								c.validarInternet(tf_internet.getText()));
						c.addTelefone(t.getNumero(), t);
						if (t.temInternet()) {
							internet = "Sim";
						} else {
							internet = "Nao";
						}
						str = "Telefone adicionado!\n\n" + "Cliente: " + c.getNome() + "\nEndereço: "
								+ r_tfEndereco.getText() + "\n" + "Número: (" + t.getNumero().substring(0, 2) + ")"
								+ t.getNumero().substring(2, 6) + "-" + t.getNumero().substring(6, 10)
								+ "\nData de Instalação: "
								+ t.getDataInstalacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
								+ "\nInternet: " + internet;
						JOptionPane.showMessageDialog(null, str);
					} else {
						JOptionPane.showMessageDialog(null, "Este número de telefone já foi cadastrado.");
					}

				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null,
							"Cliente e telefones não adicionados, favor preencher as informacoes corretamente");
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}

			}
		});
		r_btn.setBounds(268, 138, 157, 31);
		pResidencial.add(r_btn);

		JLabel lblNewLabel_1 = new JLabel("Internet (S / N): ");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 111, 139, 13);
		pResidencial.add(lblNewLabel_1);

		tf_internet = new JTextField();
		tf_internet.setBackground(new Color(220, 220, 220));
		tf_internet.setForeground(Color.BLACK);
		tf_internet.setBounds(159, 109, 96, 19);
		pResidencial.add(tf_internet);
		tf_internet.setColumns(10);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(309, 93, -23, 20);
		pResidencial.add(formattedTextField);

		JPanel pComercial = new JPanel();
		pComercial.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED, Color.RED, Color.RED));
		pComercial.setForeground(Color.BLUE);
		pComercial.setBackground(Color.GRAY);
		tabbedPane.addTab("Comercial", null, pComercial, null);
		pComercial.setLayout(null);

		JLabel lblNome_2 = new JLabel("Nome:");
		lblNome_2.setForeground(Color.BLACK);
		lblNome_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNome_2.setBounds(10, 11, 56, 13);
		pComercial.add(lblNome_2);

		c_tfNome = new JTextField();
		c_tfNome.setBackground(new Color(220, 220, 220));
		c_tfNome.setForeground(Color.BLACK);
		c_tfNome.setColumns(10);
		c_tfNome.setBounds(84, 10, 171, 19);
		pComercial.add(c_tfNome);

		c_tfNumero = new JTextField();
		c_tfNumero.setBackground(new Color(220, 220, 220));
		c_tfNumero.setForeground(Color.BLACK);
		c_tfNumero.setColumns(10);
		c_tfNumero.setBounds(94, 34, 171, 19);
		pComercial.add(c_tfNumero);

		JLabel lblNumero_2 = new JLabel("Numero:");
		lblNumero_2.setForeground(Color.BLACK);
		lblNumero_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumero_2.setBounds(10, 36, 74, 13);
		pComercial.add(lblNumero_2);

		JLabel lblEndereco_2 = new JLabel("Endereco:");
		lblEndereco_2.setForeground(Color.BLACK);
		lblEndereco_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEndereco_2.setBounds(10, 61, 84, 13);
		pComercial.add(lblEndereco_2);

		c_tfEndereco = new JTextField();
		c_tfEndereco.setBackground(new Color(220, 220, 220));
		c_tfEndereco.setForeground(Color.BLACK);
		c_tfEndereco.setColumns(10);
		c_tfEndereco.setBounds(104, 59, 324, 19);
		pComercial.add(c_tfEndereco);

		JLabel lblDataInstalao_3 = new JLabel("Data Instalação: (dd/mm/yyyy)");
		lblDataInstalao_3.setForeground(Color.BLACK);
		lblDataInstalao_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDataInstalao_3.setBounds(10, 85, 266, 17);
		pComercial.add(lblDataInstalao_3);

		c_tfData_Instalacao = new JTextField();
		c_tfData_Instalacao.setBackground(new Color(220, 220, 220));
		c_tfData_Instalacao.setForeground(Color.BLACK);
		c_tfData_Instalacao.setColumns(10);
		c_tfData_Instalacao.setBounds(276, 85, 152, 19);
		pComercial.add(c_tfData_Instalacao);

		JButton c_btn = new JButton("Adicionar");
		c_btn.setForeground(Color.BLACK);
		c_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		c_btn.setBackground(Color.RED);
		c_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str;
				try {
					String aux = c_tfNumero.getText().replaceAll("[\\D]", "");
					Cliente c = new Cliente(c_tfNome.getText(), c_tfEndereco.getText());

					if (!clientes.containsKey(aux)) {
						clientes.put(aux, c);

						String ano = c_tfData_Instalacao.getText().replaceAll("[\\D]", "");
						Telefone t = new Telefone(aux,
								LocalDate.of(Integer.parseInt(ano.substring(4, 8)),
										Integer.parseInt(ano.substring(2, 4)), Integer.parseInt(ano.substring(0, 2))),
								tf_ramo_atividade.getText());
						c.addTelefone(t.getNumero(), t);
						str = "Telefone adicionado!\n\n" + "Cliente: " + c.getNome() + "\nEndereço: "
								+ c_tfEndereco.getText() + "\n" + "Número: (" + t.getNumero().substring(0, 2) + ")"
								+ t.getNumero().substring(2, 6) + "-" + t.getNumero().substring(6, 10)
								+ "\nRamo de Atividade: " + tf_ramo_atividade.getText() + "\nData de Instalação: "
								+ t.getDataInstalacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						JOptionPane.showMessageDialog(null, str);
					} 

				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null,
							"Cliente e telefones não adicionados, favor preencher as informacoes corretamente");
				} catch(Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage());
				}

			}
		});
		c_btn.setBounds(270, 138, 158, 31);
		pComercial.add(c_btn);

		JLabel lblRamo_atividade = new JLabel("Ramo de Atividade:");
		lblRamo_atividade.setForeground(Color.BLACK);
		lblRamo_atividade.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRamo_atividade.setBounds(10, 109, 163, 13);
		pComercial.add(lblRamo_atividade);

		tf_ramo_atividade = new JTextField();
		tf_ramo_atividade.setBackground(new Color(220, 220, 220));
		tf_ramo_atividade.setForeground(Color.BLACK);
		tf_ramo_atividade.setColumns(10);
		tf_ramo_atividade.setBounds(183, 108, 245, 19);
		pComercial.add(tf_ramo_atividade);

		JLabel lblNome_2_1 = new JLabel("+");
		lblNome_2_1.setForeground(new Color(0, 0, 0));
		lblNome_2_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNome_2_1.setBounds(276, 141, 32, 19);
		pComercial.add(lblNome_2_1);

		JPanel pEspecializada = new JPanel();
		pEspecializada
				.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN));
		pEspecializada.setBackground(Color.GRAY);
		tabbedPane.addTab("Especializada", null, pEspecializada, null);
		pEspecializada.setLayout(null);

		e_tfNome = new JTextField();
		e_tfNome.setBackground(new Color(220, 220, 220));
		e_tfNome.setForeground(Color.BLACK);
		e_tfNome.setColumns(10);
		e_tfNome.setBounds(84, 10, 171, 19);
		pEspecializada.add(e_tfNome);

		JLabel lblNome_3 = new JLabel("Nome:");
		lblNome_3.setForeground(Color.BLACK);
		lblNome_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNome_3.setBounds(10, 11, 56, 13);
		pEspecializada.add(lblNome_3);

		JLabel lblNumero_3 = new JLabel("Numero:");
		lblNumero_3.setForeground(Color.BLACK);
		lblNumero_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumero_3.setBounds(10, 35, 79, 13);
		pEspecializada.add(lblNumero_3);

		e_tfNumero = new JTextField();
		e_tfNumero.setBackground(new Color(220, 220, 220));
		e_tfNumero.setForeground(Color.BLACK);
		e_tfNumero.setColumns(10);
		e_tfNumero.setBounds(94, 33, 171, 19);
		pEspecializada.add(e_tfNumero);

		JLabel lblEndereco_3 = new JLabel("Endereco:");
		lblEndereco_3.setForeground(Color.BLACK);
		lblEndereco_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEndereco_3.setBounds(10, 59, 88, 13);
		pEspecializada.add(lblEndereco_3);

		e_tfEndereco = new JTextField();
		e_tfEndereco.setBackground(new Color(220, 220, 220));
		e_tfEndereco.setForeground(Color.BLACK);
		e_tfEndereco.setColumns(10);
		e_tfEndereco.setBounds(104, 57, 324, 19);
		pEspecializada.add(e_tfEndereco);

		JLabel lblDataInstalao_2 = new JLabel("Data Instalação: (dd/mm/yyyy)");
		lblDataInstalao_2.setForeground(Color.BLACK);
		lblDataInstalao_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDataInstalao_2.setBounds(10, 83, 266, 17);
		pEspecializada.add(lblDataInstalao_2);

		e_tfData_Instalacao = new JTextField();
		e_tfData_Instalacao.setBackground(new Color(220, 220, 220));
		e_tfData_Instalacao.setForeground(Color.BLACK);
		e_tfData_Instalacao.setToolTipText("");
		e_tfData_Instalacao.setColumns(10);
		e_tfData_Instalacao.setBounds(281, 83, 147, 19);
		pEspecializada.add(e_tfData_Instalacao);

		JButton e_btn = new JButton("Adicionar");
		e_btn.setForeground(Color.BLACK);
		e_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		e_btn.setBackground(Color.GREEN);
		e_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str;
				try {
					String aux = e_tfNumero.getText().replaceAll("[\\D]", "");
					Cliente c = new Cliente(e_tfNome.getText(), e_tfEndereco.getText());
					
						if (!clientes.containsKey(aux) && Integer.parseInt(e_tfOcorrencias.getText()) >= 0) {
							clientes.put(aux, c);

								String ano = e_tfData_Instalacao.getText().replaceAll("[\\D]", "");
								Telefone t = new Telefone(aux,
										LocalDate.of(Integer.parseInt(ano.substring(4, 8)),
												Integer.parseInt(ano.substring(2, 4)),
												Integer.parseInt(ano.substring(0, 2))),
										Integer.parseInt(e_tfOcorrencias.getText()));
								c.addTelefone(t.getNumero(), t);
								str = "Telefone adicionado!\n\n" + "Cliente: " + c.getNome() + "\nEndereço: "
										+ e_tfEndereco.getText() + "\n" + "Número: (" + t.getNumero().substring(0, 2)
										+ ")" + t.getNumero().substring(2, 6) + "-" + t.getNumero().substring(6, 10)
										+ "\nQtd de Ocorrencias: " + e_tfOcorrencias.getText()
										+ "\nData de Instalação: "
										+ t.getDataInstalacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
								JOptionPane.showMessageDialog(null, str);
							
						} else if (Integer.parseInt(e_tfOcorrencias.getText()) < 0){
							JOptionPane.showMessageDialog(null, "A quantidade de ocorrencias não pode ser negativa.");
						} 
					
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null,
							"Cliente e telefones não adicionados, favor preencher as informacoes corretamente");
				} catch(Exception exc) {
					JOptionPane.showMessageDialog(null, "Data inválida, favor preencher a data com dia, mês e ano (dd/mm/aaaa).");
				}

			}
		});
		e_btn.setBounds(269, 138, 156, 31);
		pEspecializada.add(e_btn);

		e_tfOcorrencias = new JTextField();
		e_tfOcorrencias.setBackground(new Color(220, 220, 220));
		e_tfOcorrencias.setForeground(Color.BLACK);
		e_tfOcorrencias.setColumns(10);
		e_tfOcorrencias.setBounds(152, 109, 103, 19);
		pEspecializada.add(e_tfOcorrencias);

		JLabel e_lbl_ocorrencias = new JLabel("N° Ocorrencias:");
		e_lbl_ocorrencias.setForeground(Color.BLACK);
		e_lbl_ocorrencias.setFont(new Font("Tahoma", Font.BOLD, 16));
		e_lbl_ocorrencias.setBounds(10, 111, 136, 13);
		pEspecializada.add(e_lbl_ocorrencias);

		JLabel lblPesquisar = new JLabel("PESQUISAR DADOS");
		lblPesquisar.setForeground(Color.RED);
		lblPesquisar.setBackground(Color.RED);
		lblPesquisar.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPesquisar.setBounds(28, 299, 215, 27);
		frmSistemaDeTelefones.getContentPane().add(lblPesquisar);

		JLabel p_lbl_Numero = new JLabel("Numero:");
		p_lbl_Numero.setForeground(Color.BLACK);
		p_lbl_Numero.setFont(new Font("Tahoma", Font.BOLD, 17));
		p_lbl_Numero.setBounds(28, 335, 81, 13);
		frmSistemaDeTelefones.getContentPane().add(p_lbl_Numero);

		p_tf_num_pesquisarc = new JTextField();
		p_tf_num_pesquisarc.setColumns(10);
		p_tf_num_pesquisarc.setBounds(108, 334, 171, 19);
		frmSistemaDeTelefones.getContentPane().add(p_tf_num_pesquisarc);

		JButton btn_Pesquisar = new JButton("Pesquisar");
		btn_Pesquisar.setForeground(Color.BLACK);
		btn_Pesquisar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Pesquisar.setBackground(Color.YELLOW);
		btn_Pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = null;
				
					try {
						String aux = p_tf_num_pesquisarc.getText().replaceAll("[\\D]", "");
						str = "Cliente: " + clientes.get(aux).getNome() + "\nEndereço: "
								+ clientes.get(aux).getEndereco() + "\n" + "Número: ("
								+ (aux.substring(0, 2) + ")"
										+ aux.substring(2, 6) + "-"
										+ aux.substring(6, 10) + "\nData de Instalação: "
										+ clientes.get(aux).getDataInstalacao(aux).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))) +
												"\nCusto: R$" + clientes.get(aux).getCusto(aux);
						if (clientes.get(aux).getTipo(aux) == 'C') {
							str += "\nRamo de Atividade: " + clientes.get(aux).getRamoAtividade(aux);
						} else if (clientes.get(aux).getTipo(aux) == 'R') {
								str += "\nInternet: ";
									if (clientes.get(aux).temInternet(aux)) {
											str += "Sim";
										} else {
												str += "Não";
										}

						} else if (clientes.get(aux).getTipo(aux) == 'E') {
							str += "\nQtde Ocorrencias: " + clientes.get(aux).getQtdeOcorrencias(aux);
						}
						JOptionPane.showMessageDialog(null, str);
					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(null, "O número consultado não existe no nosso cadastro ou está vazio. \nTente novamente.");
					}
				
			}
		});
		btn_Pesquisar.setBounds(289, 321, 122, 42);
		frmSistemaDeTelefones.getContentPane().add(btn_Pesquisar);

		JButton btnObterFaturamento = new JButton("Obter Faturamento R$");
		btnObterFaturamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double somador = 0.0;
				for (Cliente c : clientes.values()) {
					if (c != null) {
						// O SOMADOR VAI SER SOMADO AO MÉTODO QUE PEGA O CUSTO DE TODOS OS TELEFONES
						// DAQUELE CLIENTE INSTANCIADO.
						// ESSE GETCUSTOTELEFONES PEGA O CUSTO DO HASHMAP DE TELEFONES DO CLIENTE EM
						// QUESTÃO.
						somador += c.getCustoTelefones();
					}
				}
				String str = "Faturamento total: R$" + Double.toString(somador);
				JOptionPane.showMessageDialog(null, str);
			}
		});
		btnObterFaturamento.setForeground(Color.BLACK);
		btnObterFaturamento.setFont(new Font("Arial", Font.BOLD, 17));
		btnObterFaturamento.setBackground(new Color(255, 102, 0));
		btnObterFaturamento.setBounds(289, 374, 224, 44);
		frmSistemaDeTelefones.getContentPane().add(btnObterFaturamento);
	}
	
	
}