mvn clean compiler:compile assembly:single
echo '打包完成'
echo
echo '运行 Exam1.jar'
java -jar Exam1/target/Exam1.jar http://192.168.11.205:18080/trainning/SampleChapter1.pdf
echo
echo '运行 Exam2.jar'
java -jar Exam2/target/Exam2.jar &
java -cp Exam2/target/Exam2.jar com.hand.ClientMain
echo
echo '运行 Exam3.jar'
java -jar Exam3/target/Exam3.jar sz300170