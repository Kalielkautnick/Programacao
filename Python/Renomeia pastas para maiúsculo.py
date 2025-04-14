import os

# Defina o caminho do diretório que contém as pastas
base_dir = r"SEU DIRETORIO"

# Percorre todas as entradas no diretório base
for nome_pasta in os.listdir(base_dir):
    caminho_antigo = os.path.join(base_dir, nome_pasta)
    
    # Verifica se é um diretório
    if os.path.isdir(caminho_antigo):
        nome_maiusculo = nome_pasta.upper()
        #nome_minusculo = nome_pasta.lower()
        #nome_primeiraletramaiuscula = nome_pasta.title()
        
        # Verifica se o nome já está em maiúsculas
        if nome_pasta != nome_maiusculo:
            caminho_temp = os.path.join(base_dir, f"__tmp__{nome_maiusculo}")
            caminho_novo = os.path.join(base_dir, nome_maiusculo)
            
            try:
                # Renomeia para um nome temporário para evitar conflitos
                os.rename(caminho_antigo, caminho_temp)
                # Renomeia para o nome final em maiúsculas
                os.rename(caminho_temp, caminho_novo)
                print(f"Renomeado: {nome_pasta} → {nome_maiusculo}")
            except PermissionError:
                print(f"Permissão negada ao renomear: {nome_pasta}. A pasta pode estar em uso por outro processo.")
            except OSError as e:
                print(f"Erro ao renomear {nome_pasta}: {e}")
