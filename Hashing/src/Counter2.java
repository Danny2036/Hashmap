import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.*;
public class Counter2 {

	LinkedList<LinkedList<Hashnode>> list = new LinkedList<LinkedList<Hashnode>>();
	private int numInsert;

	/*
	 * Constructor
	 * @param size: an integer size for the LinkList<Hashnode>
	 */
	public Counter2(int size){
		for(int i = 0; i < size; i++) {
			list.add(new LinkedList<Hashnode>());
		}
	}

	/*
	 * Constructor
	 * Empty: intitializes with size of 30
	 */
	public Counter2(){
		int size = 30;
		for(int i = 0; i < size; i++) {
			list.add(new LinkedList<Hashnode>());
		}
	}
	
	/*
	 * Main method
	 * Change the name of the input file 
	 * Runs through the full file on Wizard of Oz texts
	 */
	public static void main(String args[]){
		Counter2 counter = new Counter2();
		try {
			counter.wordCount("Toy File.txt");
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	/*
	 * Counts the frequency of words
	 * Contains the name of the output file
	 * @param String: provides a file to pull words from
	 */
	public void wordCount(String input_filename) throws IOException{
		this.toList(input_filename);
		PrintWriter writer = new PrintWriter("TheToyCount.txt");
        writer.println("The average chain length is " + this.average());
		for(LinkedList<Hashnode> check : list){
			for(Hashnode node: check){
				writer.println(node.getKey()+ " "+ node.getNum());
			}
		}
		writer.close();
	}

	/*
	 * Puts all the data of a file into a Hashtable
	 * String: name of a file to pull words from
	 */
	public void toList(String filename) throws IOException{

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String currentLine = reader.readLine();
		String[] array;

		while(currentLine != null){
			String line = currentLine.toLowerCase();
			array = line.split("\\W");

			for(int i = 0; i< array.length; i++){
				if(array[i]!= null){
					this.insert(array[i]);
				}
			}
			currentLine = reader.readLine();
		}
		reader.close();
	}

	/*
	 * Inserts a string into array 
	 * @param key: the String to be inserted
	 */
	public void insert(String key){
		int code = Math.abs(key.hashCode())%list.size();
		int loadFactor = numInsert/list.size();

		if(loadFactor > 1){
			this.resizeList();
		}

		if(this.contains(key)){
			for(Hashnode node : list.get(code)) {
				if(node.getKey().equals(key)){
					node.addNum();
				}
			}
		} else {
			list.get(code).add(list.get(code).size(), new Hashnode(key));
			this.numInsert++;
		}
	}

	
	/*
	 * Finds if a word is in the hastable
	 * @param String: s word to parse through the hastable for
	 * @return boolean: true if the word is present false if else
	 */
	public boolean contains(String key) {
		int code = Math.abs(key.hashCode())%list.size();
		for(Hashnode node : list.get(code)) {
			if(node.getKey().equals(key)){
				return true;
			}
		}      
		return false;
	}

	
	/*
	 * Resizes the array to be double the previous sizes and rehashes all elements
	 */
	public void resizeList(){
		LinkedList<LinkedList<Hashnode>> newList = new LinkedList<LinkedList<Hashnode>>();

		for(int i = 0; i < list.size()*2; i++){
			newList.add(new LinkedList<Hashnode>());
		}
		for(LinkedList<Hashnode> nodeList: this.list){
			for(Hashnode node: nodeList){
				newList.get(Math.abs(node.getKey().hashCode())%list.size()).add(node);
			}
		}

	}

	
	/*
	 * Finds the average number of chains lengths
	 * @return double: The average length
	 */
	public double average(){
		double num = 0.0;
		for(LinkedList<Hashnode> node: list){
			num += node.size();
		}
		return num/list.size();
	}
}