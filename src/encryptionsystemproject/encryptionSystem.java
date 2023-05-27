/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package encryptionsystemproject;

import encryptionsystemproject.Sender;
import encryptionsystemproject.Receiver;
import java.security.SecureRandom;
import java.util.Scanner;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author last modyfication by jamelah
 */
public class encryptionSystem {

    /**
     * Section A49
     * Name:      ID: 
     * Jana Kurdi   1906167 
     * Jamelah hadi 1910165 
     * Renad Ghaleb 1908460 
     * Noor Babahr  1912922
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        //create a scanner object
        Scanner input = new Scanner(System.in);
        //Allow the user to enter message
        System.out.println("Welcome to the Encryption System  ");
        System.out.println("*********************************");
        System.out.print("Enter a message:");
        String plainText = input.nextLine();
        System.out.println("*********************************");

        //create Sender & Receiver objects
        Sender sender = new Sender();
        Receiver receiver = new Receiver();

        
        //method to generate keys /Bob both generate their keys
        receiver.generateKey();
        sender.generateKey();
        //send public key 
        //sender will send a massage to the recever &so we need the recever public key to encrypt the secret key 
        sender.setPK_receiver(receiver.getSender_publicKey());

         
//generate Initial vector(IV)
        //create array of 16 bytes beacuse AES algorithm supports key lengths of 128 bits
        byte[] iv = new byte[16];
       // SecureRandom secRandom = new SecureRandom(iv);
        IvParameterSpec IV = new IvParameterSpec(iv);

        /////////////////////////////////////////////////
        //encrypte the message 
         byte[] senderM=sender.EncryptMessage(plainText, IV);
         
         //send the encrypted message to receiver
         receiver.setMessage(senderM);
         
         
         //recever will encrypt the secret key using his private key
         receiver.setAES_SecretKey(sender.getEncryptedKey());
        // decrypte the message
         receiver.DecryptMessage(IV);
    }

}
