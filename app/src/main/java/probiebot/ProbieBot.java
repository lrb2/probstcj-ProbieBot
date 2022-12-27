/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package probiebot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;




/**
 *
 * @author probs
 */
public class ProbieBot {
    
    public static JDA jda;
    public static String prefix = "%";
    public static ArrayList<String> roasts = new ArrayList<>();
    public static ArrayList<String> literallyRoasts = new ArrayList<>();
    public static ArrayList<String> literallyNotRoasts = new ArrayList<>();
    
    public static void main(String[] args) throws LoginException{
        String token = null;
        Scanner scan = null;
        try{
            scan = new Scanner(new File("token.token"));
            token = scan.next();
        }
        catch(FileNotFoundException e){}
        jda = JDABuilder.createDefault(token).build();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setActivity(Activity.listening("chat. Use " + prefix + "help to see my commands!"));
        jda.addEventListener(new Commands());
        addRoasts();
        Timer timer = new Timer(60000, new BirthdayTimer());
        timer.start();
    }
    public static void addRoasts(){
        roasts.add("(user) is the sun in my life... I want them 93 million miles away from me.");
        roasts.add("(user) has such a beautiful face... But let's put a bag over that personality");
        roasts.add("There is someone out there for everyone. For (user), it's a therapist");
        roasts.add("I would smack (user), but I am against animal abuse");
        roasts.add("I can't wait to spend my whole life without (user)");
        roasts.add("I don't hate (user), but if they were drowning, I would give them a high five.");
        roasts.add("(ping User), If I throw a stick, will you leave me too?");
        roasts.add("Sorry I can't think of an insult dumb enough for (user) to understand.");
        roasts.add("I don't know what makes (user) so stupid, but it works");
        roasts.add("It is hilarious how (user) tries to fit their entire vocabulary in one sentence.");
        roasts.add("I like the way (user) combs their hair, so the horns don't show up.");
        roasts.add("(ping User), have a nice day... somewhere else");
        roasts.add("I would call (user) an idiot, but it would be an insult for stupid people.");
        roasts.add("I told my therapist about (user); she didn't believe me.");
        roasts.add("The last time I saw something like (user), it was behind metal grids");
        roasts.add("Every time I have a stick in my hand, (user) looks like a pinata");
        roasts.add("(user) is like a software update. Every time I see (user), I immediately think \"not now\"");
        roasts.add("When I look at (user), I think to myself where has (user) been my whole life? Can they go back there?");
        roasts.add("(user) is the reason why there are instructions on shampoo bottles.");
        roasts.add("I think (user) just needs a high five... in the face... with a chair");
        roasts.add("When I listen to (user), I think they are really going to go far. I hope they stay there.");
        roasts.add("I look at (user) and think \"What a waste of two billion years of evolution\"");
        roasts.add("(user) is as useless as the /'ueue/' in /'queue/'.");
        roasts.add("Hey, (ping User), you have something on your chin... No, the 3rd one down.");
        roasts.add("(user) is the reason the gene pool needs a lifeguard.");
        roasts.add("(user) must have been born on a highway because that's where most accidents happen.");
        roasts.add("(ping User), a thought crossed your mind?? Must have been a long and lonely journey.");
        roasts.add("(ping User), I love what you've done with your hair. How do you get it to come out of your nostrils like that?");
        
        literallyRoasts.add("Are you illit**t**erate?");
        literallyRoasts.add("Dude, if you spell literally wrong one more time, I will come and slap you with the extra t");
        literallyRoasts.add("Really? Again!");
        literallyRoasts.add("You lit**t**erally can't spell.");
        literallyRoasts.add("Hey, just wanted to let you know, you're lit**t**erally illit**t**erate.");
        literallyRoasts.add("***Bangs head against the literal wall***");
        literallyRoasts.add("Stop it. Get some help.");
        
        literallyNotRoasts.add("Good job, now don't do it again.");
        literallyNotRoasts.add("Woo hoo, you're learning!");
        literallyNotRoasts.add("Finally, you are getting it through your stubborn head.");
    }
    private static class BirthdayTimer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            DateTimeFormatter date = DateTimeFormatter.ofPattern("uuuu/MM/dd");
            DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime = LocalTime.now();
            LocalDate localDate = LocalDate.now();
            if(time.format(localTime).substring(0,5).equals("10:00")){
                Scanner scan = null;
                try{
                    scan = new Scanner(new File("birthdays.birth"));
                }
                catch(FileNotFoundException ex){
                    try{
                        new File("birthday.birth").createNewFile();
                        scan = new Scanner(new File("birthday.birth"));
                    }
                    catch(IOException exe){}
                }
                while(scan.hasNext()){
                    String temp = scan.next();
                    if(temp.substring(5).equals(date.format(localDate).substring(5))){
                        // Birthday
                        // Get User
                        String id = scan.next();
                        User user = null;
                        try{
                            user = jda.getGuildById("799255831190831115").retrieveMemberById(id).complete().getUser();
                        }
                        catch(NullPointerException ex){}
                        // Get age
                        int start = Integer.parseInt(temp.substring(0,4));
                        int end = Integer.parseInt(date.format(localDate).substring(0,4));
                        int age = end-start;
                        // Get commons channel
                        List<TextChannel> list = jda.getGuildById("799255831190831115").getTextChannels();
                        TextChannel commons = null;
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i).getName().equals("commons")){
                                commons = list.get(i);
                            }
                        }
                        if(age%10==1){
                            //First
                            commons.sendMessage("Happy " + age + "st Birthday "+user.getAsMention()+"!").queue();
                        }
                        else if(age%10==2){
                            //Second
                            commons.sendMessage("Happy " + age + "nd Birthday "+user.getAsMention()+"!").queue();
                        }
                        else if(age%10==3){
                            //Third
                            commons.sendMessage("Happy " + age + "rc Birthday "+user.getAsMention()+"!").queue();
                        }
                        else{
                            //Others
                            commons.sendMessage("Happy " + age + "th Birthday "+user.getAsMention()+"!").queue();
                        }
                    }
                    else{
                        scan.nextLine();
                    }
                }
                
            }
        }
        
    }
}
