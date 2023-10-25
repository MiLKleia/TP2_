Pour ajouter un nouveau type [GENRE] de pièces, il faut ajouter une nouvelle classe [GENRE]Calculator.java avec les fonction getAmount() et getVolume() adaptées.
Ensuite, il faut  ajouter la ligne 
types.put("[GENRE]", new [GENRE]Calculator());
dans le constructeur de Amount_and_VolumeCalculator.java
et la mise à jour est pre à l'emploi.
