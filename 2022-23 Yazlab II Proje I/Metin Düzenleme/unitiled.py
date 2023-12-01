import re

def tr_upper(self):
    self = re.sub(r"i", "İ", self)
    self = re.sub(r"ı", "I", self)
    self = re.sub(r"ç", "Ç", self)
    self = re.sub(r"ş", "Ş", self)
    self = re.sub(r"ü", "Ü", self)
    self = re.sub(r"ğ", "Ğ", self)
    
    self = re.sub(r"İ", "İ", self)
    self = re.sub(r"I", "I", self)
    self = re.sub(r"Ç", "Ç", self)
    self = re.sub(r"Ş", "Ş", self)
    self = re.sub(r"Ü", "Ü", self)
    self = re.sub(r"Ğ", "Ğ", self)
    
    self = self.upper() # for the rest use default upper
    return self


def tr_lower(self):
    self = re.sub(r"İ", "i", self)
    self = re.sub(r"I", "ı", self)
    self = re.sub(r"Ç", "ç", self)
    self = re.sub(r"Ş", "ş", self)
    self = re.sub(r"Ü", "ü", self)
    self = re.sub(r"Ğ", "ğ", self)

    self = re.sub(r"i", "i", self)
    self = re.sub(r"ı", "ı", self)
    self = re.sub(r"ç", "ç", self)
    self = re.sub(r"ş", "ş", self)
    self = re.sub(r"ü", "ü", self)
    self = re.sub(r"ğ", "ğ", self)

    self = self.lower() # for the rest use default lower
    return self

with open("klist2.txt", "r", encoding="utf-8") as dosya:
    metin = dosya.read().split()

kelime_kumesi = set(metin)
temiz_metin = "\n".join(kelime_kumesi)
with open("temiz_metin.txt", "w", encoding="utf-8") as dosya:
    dosya.write(temiz_metin + "\n")

# Dosyadaki kelimeleri bir liste olarak oku
with open("temiz_metin.txt", "r", encoding="utf-8") as f:
    kelime_listesi = f.read().split()

# Kelime listesini alfabetik olarak sırala
kelime_listesi.sort()

# Sıralanmış kelimeleri yeni bir dosyaya yazdır
with open("temiz_metin.txt", "w", encoding="utf-8") as f:
    for kelime in kelime_listesi:
        f.write(kelime + "\n")


with open("temiz_metin.txt", "r", encoding="utf-8") as dosya:
    met = dosya.read()
kelimeler = met.split()

uzun_kelimeler = []
for kelime in kelimeler:
    if len(kelime) >= 3:
        kelime=tr_lower(kelime)
        uzun_kelimeler.append(kelime)



temiz = " ".join(uzun_kelimeler)

with open("temiz_metin.txt", "w", encoding="utf-8") as dosya:
    dosya.write(temiz)

with open("temiz_metin.txt", "r", encoding="utf-8") as f:
    kelime_listesi = f.read().split()

# Kelime listesini alfabetik olarak sırala
kelime_listesi.sort()

# Sıralanmış kelimeleri yeni bir dosyaya yazdır
with open("temiz_metin.txt", "w", encoding="utf-8") as f:
    for kelime in kelime_listesi:
        f.writelines(kelime+",")