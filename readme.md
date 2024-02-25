Разработка, тестирование и отладка велись на Lunux Ubuntu 22.04
использовался openjdk 19.0.2

Программа запускается со следующими аргументами:
1. -s - краткая, -f - полная статистика. Если параметр не указан, по умолчанию выводится краткая
2. -a (опциональный) дозапись в ранее созданные выходные файлы.
3. -p (опциональный) добавить префикс к именам выходных файлов.
4. -o (опциональный) выбор пути к входным файлам. Папка должна существовать (в случае отсутствия не создается).
Если необходимо сохранить файлы во вложенной папке внутри текущей, указать её имя с ведущим "./". 
Например: "./имя вложенной папки", если не в текущей папке - указать полный путь.
Если параметр не указан, по умолчанию файлы создаются в текущей папке.
5. остальные параметры – имена входных файлов,

Примеры запуска из командной строки терминала:
1. javac Solution.java
2. java Solution -f -p _prefix_ 1.txt 2.txt 