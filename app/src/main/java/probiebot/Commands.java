/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package probiebot;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageEmbed.Field;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author probs
 */
public class Commands extends ListenerAdapter{
    public Queue<User> tarotQueue = new LinkedList<>();
    public boolean activeHelp = false;
    public String countingChannel = "1003825838933938276";
    public String boisGuild = "1047047324930678824";
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) throws IllegalArgumentException{
        // Get referenced message
//        try{
//        System.out.println(event.getMessage().getMessageReference().getMessage().getContentRaw());
//        }
//        catch(NullPointerException ex){}
        try{
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(event.getGuild().getId().equals(boisGuild)){
            if(args[0].equals(ProbieBot.prefix+"helpfood")){
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("All Food Options:");
                embed.appendDescription("`%apizza` - Free 12 inch pizza (after 24 hours)\n" +
"`%arbys` - Classic Roast Beef w/ Purchase (Instant)\n" +
"`%backburger [email]` - Free Classic Burger w/ Purchase (Instant, coupon in email)\n" +
"`%bagel` - Present Barcode for free bagel, w/ Purchase (Instant)\n" +
"`%baskin [email]` - BOGO coupon for ice cream (Need to wait for email)\n" +
"`%beni [email]` - $30 Certificate (Need to wait on email)\n" +
"`%bj` - Free Pizookie (Instant coupon, apply at checkout)\n" +
"`%bojangle` - Free Chicken Sandwich and Free Cajun Filet Biscuit (Instant, coupon on account, apply on checkout)\n" +
"`%buf [email]` - 6 Free Wings (Will email coupon on THE FIRST OF THE MONTH, NEXT MONTH)\n" +
"`%capd [email]` - 1 pc Fish and Fries (Have to wait on email)\n" +
"`%chilis` - Free Chips & Salsa or Non-Alchohlic Beverage (Instant, need to change name)\n" +
"`%cin` - Free Small Cold Brew (Instant, on account, taxes still apply)\n" +
"`%cpk` - Free small plate and dessert (need to wait on offer to show up)\n" +
"`%deltaco [email]` - Wait for verification email address in email, 2 free The Del Tacos or Regular Size Shake\n" +
"`%den` -  Free Triple Double Slam (Need to wait on offer)\n" +
"`%dots [email]` - Free Dippin Dots (Need to wait on email)\n" +
"`%drink [email]` - Emails you a Coupon for Free Power Burn Energy Drink (Only works in specific stores, need to wait for email)\n" +
"`%farmer` - 50% off FarmersDog Meal Box - Have to take survey\n" +
"`%fire` - Free Firehouse Sub (Need to wait till next day for reward)\n" +
"`%goodchop` - $50 off (Instant)\n" +
"`%hello` - Free Hello Fresh Box (uses referal link, must pay shipping)\n" +
"`%ihop [email]` - Free Pancake Stack (Wait 24-48 hours, coupon in email)\n" +
"`%kk` - Free donut on first day, dozen (12) after a day\n" +
"`%mcd [email]` - Must use app, verify email in email, enable location and check deals.\n" +
"`%pop [email]` - Free regular side, small drink, or Apple Pie (Need to verify email)\n" +
"`%revive` - 50% off Revive (Instant coupon code)\n" +
"`%smoothieking` - Must use app, Free smoothie (If doesn't show up, try generating on a Friday)\n" +
"`%steak [email]` - Will be emailed code within 48 hours, Free steak dinner\n" +
"`%steaknshake [email]` - Free shake in app (Instant)\n" +
"`%subway` - BOGO Subs (Instant)\n" +
"`%taco` - Need to use app to get reward, Free Taco Bell Taco (Usually shows up within 5-10 minutes)\n" +
"`%texas [email]` - Free Entree w/ Purchase (need to wait on email)\n" +
"`%treats` - Must download app `Buddies` using link, account will have points, shipping is free, free Dog or Cat Treats\n" +
"`%tropical` - Must use Tropical App, must make purchase of $5 or more, wait 24 hours, Free Smoothie\n" +
"`%waffle [email]` - Free waffle or Hashbrowns (need to wait for email)\n" +
"`%wetzel [email]` - Must use app, Free Wetzel pretzel, login and go to rewards (Instant)\n" +
"`%what [email]` - Download Whataburger app, login and verify email, Free Whataburger (within an hour)\n");
                event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
            
            }
            else if(args[0].equals(ProbieBot.prefix+"apizza")||
                    args[0].equals(ProbieBot.prefix+"arbys")||
                    args[0].equals(ProbieBot.prefix+"backburger")||
                    args[0].equals(ProbieBot.prefix+"bagel")||
                    args[0].equals(ProbieBot.prefix+"baskin")||
                    args[0].equals(ProbieBot.prefix+"beni")||
                    args[0].equals(ProbieBot.prefix+"bj")||
                    args[0].equals(ProbieBot.prefix+"bojangle")||
                    args[0].equals(ProbieBot.prefix+"buf")||
                    args[0].equals(ProbieBot.prefix+"capd")||
                    args[0].equals(ProbieBot.prefix+"chilis")||
                    args[0].equals(ProbieBot.prefix+"cin")||
                    args[0].equals(ProbieBot.prefix+"cpk")||
                    args[0].equals(ProbieBot.prefix+"deltaco")||
                    args[0].equals(ProbieBot.prefix+"den")||
                    args[0].equals(ProbieBot.prefix+"dots")||
                    args[0].equals(ProbieBot.prefix+"drink")||
                    args[0].equals(ProbieBot.prefix+"farmer")||
                    args[0].equals(ProbieBot.prefix+"fire")||
                    args[0].equals(ProbieBot.prefix+"goodchop")||
                    args[0].equals(ProbieBot.prefix+"hello")||
                    args[0].equals(ProbieBot.prefix+"ihop")||
                    args[0].equals(ProbieBot.prefix+"kk")||
                    args[0].equals(ProbieBot.prefix+"mcd")||
                    args[0].equals(ProbieBot.prefix+"pop")||
                    args[0].equals(ProbieBot.prefix+"revive")||
                    args[0].equals(ProbieBot.prefix+"smoothieking")||
                    args[0].equals(ProbieBot.prefix+"steak")||
                    args[0].equals(ProbieBot.prefix+"steaknshake")||
                    args[0].equals(ProbieBot.prefix+"subway")||
                    args[0].equals(ProbieBot.prefix+"taco")||
                    args[0].equals(ProbieBot.prefix+"texas")||
                    args[0].equals(ProbieBot.prefix+"treats")||
                    args[0].equals(ProbieBot.prefix+"tropical")||
                    args[0].equals(ProbieBot.prefix+"waffle")||
                    args[0].equals(ProbieBot.prefix+"wetzel")||
                    args[0].equals(ProbieBot.prefix+"what")){
                if(args.length == 1){
                    String temp = args[0].substring(1,args[0].length()).toLowerCase();
                    EmbedBuilder embed = new EmbedBuilder();
                    StringBuilder sb = new StringBuilder();
                    switch(temp){
                        case "apizza": 
                            embed.setTitle("APizza Account");
                            sb.append("APizza Account Request:\n");
                            break;
                        case "arbys":
                            embed.setTitle("Arbys Account");
                            sb.append("Arbys Account Request:\n");
                            break;
                        case "bagel": 
                            embed.setTitle("Bagel Account");
                            sb.append("Bagel Account Request:\n");
                            break;
                        case "bj": 
                            embed.setTitle("BJ's Account");
                            sb.append("BJ's Account Request:\n");
                            break;
                        case "bojangle": 
                            embed.setTitle("Bojangle Account");
                            sb.append("Bojangle Account Request:\n");
                            break;
                        case "chilis": 
                            embed.setTitle("Chilis Account");
                            sb.append("Chilis Account Request:\n");
                            break;
                        case "cin": 
                            embed.setTitle("Cinnabon Account");
                            sb.append("Cinnabon Account Request:\n");
                            break;
                        case "cpk": 
                            embed.setTitle("California Pizza Kitchen Account");
                            sb.append("California Pizza Kitchen Account Request:\n");
                            break;
                        case "den": 
                            embed.setTitle("Denny's Account");
                            sb.append("Denny's Account Request:\n");
                            break;
                        case "farmer": 
                            embed.setTitle("Farmersdog Account");
                            sb.append("Farmersdog Account Request:\n");
                            break;
                        case "fire": 
                            embed.setTitle("Firehouse Account");
                            sb.append("Firehouse Account Request:\n");
                            break;
                        case "goodchop": 
                            embed.setTitle("Goodchop Account");
                            sb.append("Goodchop Account Request:\n");
                            break;
                        case "hello": 
                            embed.setTitle("Hello Fresh Account");
                            sb.append("Hello Fresh Account Request:\n");
                            break;
                        case "kk": 
                            embed.setTitle("Krispy Kreme Account");
                            sb.append("Krispy Kreme Account Request:\n");
                            break;
                        case "revive": 
                            embed.setTitle("Revive Account");
                            sb.append("Revive Account Request:\n");
                            break;
                        case "smoothieking": 
                            embed.setTitle("Smoothie King Account");
                            sb.append("Smoothie King Account Request:\n");
                            break;
                        case "subway": 
                            embed.setTitle("Subway Account");
                            sb.append("Subway Account Request:\n");
                            break;
                        case "taco": 
                            embed.setTitle("Taco Bell Account");
                            sb.append("Taco Bell Account Request:\n");
                            break;
                        case "treats": 
                            embed.setTitle("Treats Account");
                            sb.append("Treats Account Request:\n");
                            break;
                        case "tropical": 
                            embed.setTitle("Tropical Account");
                            sb.append("Tropical Account Request:\n");
                            break;
                        default: break;
                        
                        
                    }
                    embed.setDescription("Please wait for your account. Will be dm'd to you.");
                    event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                    sb.append("Channel ID: " + event.getMessage().getChannel().getId());
                    sb.append("\n");
                    sb.append("User ID: " + event.getMessage().getAuthor().getId());
                    sb.append("\n");
                    sb.append("Message ID: " + event.getMessage().getId());
                    sb.append("\n");
                    sb.append("Reply with `%deny` or `%accept [email or link or coupon code] [password]`");
                    event.getGuild().retrieveMemberById("540957458944884746").complete().getUser().openPrivateChannel().complete().sendMessage(sb).queue();
                }
                else if(args.length == 2){
                    String temp = args[0].substring(1,args[0].length()).toLowerCase();
                    StringBuilder sb = new StringBuilder();
                    EmbedBuilder embed = new EmbedBuilder();
                    switch(temp){
                        case "backburger": 
                            embed.setTitle("Back Yard Burger Account");
                            sb.append("Back Yard Burger Account Request:\n");
                            break;
                        case "baskin":
                            embed.setTitle("Baskin Robins Account");
                            sb.append("Baskin Robins Account Request:\n");
                            break;
                        case "beni":
                            embed.setTitle("Benihana Account");
                            sb.append("Benihana Account Request:\n");
                            break;
                        case "buf":
                            embed.setTitle("Buffalo Wild Wings Account");
                            sb.append("Buffalo Wild Wings Account Request:\n");
                            break;
                        case "capd":
                            embed.setTitle("Captain D's Account");
                            sb.append("Captain D's Account Request:\n");
                            break;
                        case "deltaco":
                            embed.setTitle("Del Taco Account");
                            sb.append("Del Taco Account Request:\n");
                            break;
                        case "dots":
                            embed.setTitle("Dippin Dots Account");
                            sb.append("Dippin Dots Account Request:\n");
                            break;
                        case "drink":
                            embed.setTitle("Power Burn Energy Drink Account");
                            sb.append("Power Burn Energy Drink Account Request:\n");
                            break;
                        case "ihop":
                            embed.setTitle("iHop Account");
                            sb.append("iHop Account Request:\n");
                            break;
                        case "mcd":
                            embed.setTitle("McDonalds Account");
                            sb.append("McDonalds Account Request:\n");
                            break;
                        case "pop":
                            embed.setTitle("Popeyes Account");
                            sb.append("Popeyes Account Request:\n");
                            break;
                        case "steak":
                            embed.setTitle("Steak Account");
                            sb.append("Steak Account Request:\n");
                            break;
                        case "steaknshake":
                            embed.setTitle("Steak n' Shake Account");
                            sb.append("Steak n' Shake Account Request:\n");
                            break;
                        case "texas":
                            embed.setTitle("Texas Roadhouse Account");
                            sb.append("Texas Roadhouse Account Request:\n");
                            break;
                        case "waffle":
                            embed.setTitle("Waffle House Account");
                            sb.append("Waffle Housing Account Request:\n");
                            break;
                        case "wetzel":
                            embed.setTitle("Wetzel Account");
                            sb.append("Wetzel Account Request:\n");
                            break;
                        case "what":
                            embed.setTitle("Whataburger Account");
                            sb.append("Whataburger Account Request:\n");
                            break;
                        default:
                            break;
                    }
                    embed.setDescription("Please wait for your account. Will be dm'd to you.");
                    event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                    sb.append(args[1]);
                    sb.append("\n");
                    sb.append("Channel ID: " + event.getMessage().getChannel().getId());
                    sb.append("\n");
                    sb.append("User ID: " + event.getMessage().getAuthor().getId());
                    sb.append("\n");
                    sb.append("Message ID: " + event.getMessage().getId());
                    sb.append("\n");
                    sb.append("Reply with `%deny` or `%accept [email or link or coupon code] [password]`");
                    event.getGuild().retrieveMemberById("540957458944884746").complete().getUser().openPrivateChannel().complete().sendMessage(sb).queue();
                }
            }
        }
        // Literally roast
        if(event.getMessage().getContentRaw().toLowerCase().contains("<@981690057226342430>")){
            StringBuilder message = new StringBuilder();
            message.append(event.getChannel().getId());
            message.append("\n");
            message.append(event.getMessage().getId());
            message.append("\n\n");
            message.append(event.getMessage().getContentRaw());
            event.getJDA().openPrivateChannelById("540957458944884746").complete().sendMessage(message.toString()).queue();
        }
        if(event.getAuthor().getId().equals("540957458944884746")&&(event.getMessage().getContentRaw().toLowerCase().contains("litterally") || event.getMessage().getContentRaw().toLowerCase().contains("literally"))){
            event.getChannel().sendTyping().queue();
            if(event.getMessage().getContentRaw().toLowerCase().contains("litterally")){
                // Roast
                Scanner scan = null;
                int num=0, oNum=0;
                while(scan==null){
                    try{
                        scan = new Scanner(new File("literally.count"));
                    }
                    catch(FileNotFoundException e){
                        new File("literally.count").createNewFile();
                    }
                }
                PrintWriter toFile=null;
                try{
                    num = scan.nextInt()+1;
                    oNum = scan.nextInt();
                    toFile = new PrintWriter(new File("literally.count"));
                    toFile.print(""+num);
                    toFile.print(" " + oNum);
                    toFile.close();
                }
                catch(FileNotFoundException ex){}
                Random rand = new Random();
                String roast = ProbieBot.literallyRoasts.get(rand.nextInt(ProbieBot.literallyRoasts.size()));
                roast += "\n";
                roast += "Times right: " + oNum + "\nTimes wrong: " + num;
                event.getChannel().sendMessage(roast).reference(event.getMessage()).queue();
            }
            else{
                // Dont roast
                Scanner scan = null;
                int num=0, oNum=0;
                while(scan==null){
                    try{
                        scan = new Scanner(new File("literally.count"));
                    }
                    catch(FileNotFoundException e){
                        new File("literally.count").createNewFile();
                    }
                }
                PrintWriter toFile=null;
                try{
                    num = scan.nextInt();
                    oNum = scan.nextInt()+1;
                    toFile = new PrintWriter(new File("literally.count"));
                    toFile.print(""+num);
                    toFile.print(" " + oNum);
                    toFile.close();
                }
                catch(FileNotFoundException ex){}
                Random rand = new Random();
                String roast = ProbieBot.literallyNotRoasts.get(rand.nextInt(ProbieBot.literallyNotRoasts.size()));
                roast += "\n";
                roast += "Times right: " + oNum + "\nTimes wrong: " + num;
                event.getChannel().sendMessage(roast).reference(event.getMessage()).queue();
            }
        }
        // Info{
        
        if (args[0].equalsIgnoreCase(ProbieBot.prefix + "info")) {
            statTrack(ProbieBot.prefix+"info");
            event.getChannel().sendTyping().queue();
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("ProbieBot Information");
            info.addField("Creator","My creator is ShaquilleOSqueal (Caleb Probst).",false);
            info.addField("Description", "I am coded in Java using JDA 4.4.0_352", false);
            info.addField("Usage", "In order to use my commands, use the \""+ProbieBot.prefix+"\" "
                    + "prefix in front of your command. For a list of commands, use \""+ProbieBot.prefix+"help\"", false);
            info.setFooter("I am a bot beep boop");
            info.setColor(Color.GREEN);
            
            event.getChannel().sendMessage(info.build()).reference(event.getMessage()).queue();
            info.clear();
        }
        // Help
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "help")) {
            statTrack(ProbieBot.prefix+"help");
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.RED.brighter());
            help.setTitle("ProbieBot Help Categories:");
            help.addField("", "Here are a list of categories that you can ask for help for",false);
            help.addField(ProbieBot.prefix+"helpwhois", "Use this command to help with the commands used for the whois feature.", false);
            help.addField(ProbieBot.prefix+"helpquote", "Use this command to help with the commands used for the quoting feature.", false);
            help.addField(ProbieBot.prefix+"helpvote", "Use this command to help with the commands used for the voting feature.", false);
            help.addField(ProbieBot.prefix+"helptarotqueue", "Use this command to help with the commands used for the Tarot Queue feature.", false);
            help.addField(ProbieBot.prefix+"helpmisc", "Use this command to help with the commands used for miscellaneous features.", false);
            help.addField(ProbieBot.prefix+"helpmsg", "Use this command to help with the commands used for message features.", false);
            help.addField(ProbieBot.prefix+"helpcounting", "Use this command to show the rules of the counting channel", false);
            help.addField(ProbieBot.prefix+"helpall","This command will DM you all the commands, to save on spam messages in the chat",false);
            
            help.setFooter("I am a bot beep boop");
            event.getChannel().sendMessage(help.build()).reference(event.getMessage()).queue();
            help.clear();
        }
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "helpwhois")) {
            statTrack(ProbieBot.prefix+"helpwhois");
            event.getChannel().sendTyping().queue();
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.RED.brighter());
            help.setTitle("ProbieBot Help - Who Is");
            help.addField(ProbieBot.prefix+"whois [@]","This will tell you the description of the person you pinged. These descriptions are written by the person.",false);
            help.addField(ProbieBot.prefix+"addwhois [description]","This will add YOUR description. Only you can access this command for your description. Please use the square brackets for this command, so I know when your description starts and ends.",false);
            help.addField(ProbieBot.prefix+"editwhois [description]", "This will replace YOUR previous description with the one you type in. Only you can access this command for your description. Please use the square brackets for this command, so I know when your description starts and ends.",false);
            help.addField(ProbieBot.prefix+"deletewhois","This will delete YOUR description that I have saved for you. Only you can access this command for your description.",false);
            help.setFooter("I am a bot beep boop");
            event.getChannel().sendMessage(help.build()).reference(event.getMessage()).queue();
            help.clear();
        }
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "helpquote")) {
            statTrack(ProbieBot.prefix+"helpquote");
            event.getChannel().sendTyping().queue();
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.RED.brighter());
            help.setTitle("ProbieBot Help - Quotes");
            help.addField(ProbieBot.prefix+"addquote","If you reply to a message with this command, it will add it to the quote list.",false);
            help.addField(ProbieBot.prefix+"deletequote [quote num]","This will delete a quote given the quote number; however, only the user who quoted the person and the person who is quoted may delete the quote in question.",false);
            help.addField(ProbieBot.prefix+"quotes [@]","This will give all quotes saved by the user pinged.",false);
            help.addField(ProbieBot.prefix+"viewquotes [quote # start] [quote # end]", "This will give you all quote numbers in between, and including, the numbers given by the arguments.",false);
            help.setFooter("I am a bot beep boop");
            event.getChannel().sendMessage(help.build()).reference(event.getMessage()).queue();
            help.clear();
        }
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "helpcounting")) {
            statTrack(ProbieBot.prefix+"helpcounting");
            event.getChannel().sendTyping().queue();
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.RED.brighter());
            help.setTitle("ProbieBot Help - Counting");
            List<GuildChannel> channels = event.getGuild().getChannels();
            GuildChannel count = null;
            for (int i = 0; i < channels.size(); i++) {
                if(channels.get(i).getId().equals(countingChannel)){
                    count = channels.get(i);
                }
            }
            help.addField("Counting Rules","In the " + count.getAsMention() + " channel, there are a few rules:\n"
                    + "1. Only count in the counting channel, it only works there.\n"
                    + "2. Your number must have a minimum of 2 operations in it. We are engineers, we can do it.\n"
                    + "3. Your number must be in either infix or postfix notation (my owner is working on prefix)\n"
                    + "4. All numbers will be rounded down if they have a decimal on the back. That is how Java works.\n"
                    + "5. You cannot call two numbers in a row.\n\n"
                    + "**Supported Operations:** \n"
                    + "+ = Addition\n"
                    + "- = Subtraction\n"
                    + "* = Multiplication\n"
                    + "/ = Division\n"
                    + "^ = Exponent\n"
                    + "% = Modulus\n",false);
            help.setFooter("I am a bot beep boop");
            event.getChannel().sendMessage(help.build()).reference(event.getMessage()).queue();
            help.clear();
        }
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "helptarotqueue")) {
            statTrack(ProbieBot.prefix+"helptarotqueue");
            event.getChannel().sendTyping().queue();
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.RED.brighter());
            help.setTitle("ProbieBot Help - Tarot Queue");
            help.addField(ProbieBot.prefix+"tarotqueue","This is a feature that will view the queue of people for tarot card readings.", false);
            help.addField(ProbieBot.prefix+"addtarotqueue","This is a feature that will add you to the queue of people for tarot card readings.",false);
            help.addField(ProbieBot.prefix+"deletetarotqueue","This is a feature that will delete you from the queue of people for tarot card readings.",false);
            help.addField(ProbieBot.prefix+"nexttarotqueue","RESERVED FOR MEAGAN: This is a feature that will pop off the first person in the queue of people for tarot card readings.",false);
            help.setFooter("I am a bot beep boop");
            event.getChannel().sendMessage(help.build()).reference(event.getMessage()).queue();
            help.clear();
        }
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "helpmisc")) {
            statTrack(ProbieBot.prefix+"helpmisc");
            event.getChannel().sendTyping().queue();
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.RED.brighter());
            help.setTitle("ProbieBot Help - Miscellaneous");
            help.addField(ProbieBot.prefix+"info","This will give all the information public about me.",false);
            help.addField(ProbieBot.prefix+"help", "This will give you all of my available commands to serve you useless humans... I mean, my wonderful users:eyes:", false);
            help.addField(ProbieBot.prefix+"roast [@]","This command will roast whoever you ping. Use at user discretion.",false);
            help.addField(ProbieBot.prefix+"weather [City, ST]", "This will give you the weather in a certain area. Please use the format: city, ST (abbreviation for state) *Currently only works in the US.", false);
            help.addField(ProbieBot.prefix+"gas [City, ST]", "This will give you the prices of Regular gas in a certain area. Please use the format: city, ST (abbreviation for state) *Currently only works in the US. **Currently only works for Regular gas", false);
            help.addField(ProbieBot.prefix+"suggest Message", "This will add a suggestion for the bot. Please use this wisely, as it does DM my owner. No need for square brackets or anything.", false);
            help.addField(ProbieBot.prefix+"addbday", "This will add your birthday to the list of birthdays to be announced. Please enter in as YYYY/MM/DD. Please make sure it is correct before adding it, as you cannot change it once it is in. If it needs to be changed, please let my owner know and he can delete it manually.", false);
            help.addField(ProbieBot.prefix+"code", "This will DM you my source code (edited to make sure you hooligans don't steal my UAuth Key...)", false);
            help.addField(ProbieBot.prefix+"puppyme", "This will send you a random picture of a puppy.", false);
            help.addField(ProbieBot.prefix+"anagram Message", "This will send you an anagram of the text after the initial command. Currently the max number of words is 3.", false);
            help.addField(ProbieBot.prefix+"flman [date or no-date]", "This will send you either the most recent Florida Man article, or on a specific date given by the user in the format MM/DD.", false);
            help.addField(ProbieBot.prefix+"radar [zip code or no args]", "If given no arguments, it will send a radar image of the entire United States. If given a zip code (5 digit format), it will return a radar image of that area.", false);
            help.addField(ProbieBot.prefix+"latex [color or no color]", "If you want expression to be colored, put a comma after the color, then LaTeX code behind it. Default is white.", false);
            help.setFooter("I am a bot beep boop");
            event.getChannel().sendMessage(help.build()).reference(event.getMessage()).queue();
            help.clear();
        }
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "helpmsg")) {
            statTrack(ProbieBot.prefix+"helpmsg");
            event.getChannel().sendTyping().queue();
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.RED.brighter());
            help.setTitle("ProbieBot Help - Messages");
            help.addField(ProbieBot.prefix+"embedtomsg","(NOT YET IMPLEMENTED)If you reply to a message with this command, it will extract the contents out of the embedded message",false);
            help.addField(ProbieBot.prefix+"msgtoembed","(NOT YET IMPLEMENTED)If you reply to a message with this command, it will put the contents in an embedded message",false);
            help.addField(ProbieBot.prefix+"createembed [title] [color] [subtitle 1] [msg 1] [subtitle 2] [msg 2]...","(NOT YET IMPLEMENTED)This command will create the embedded message with the given constraints. Please use square brackets for arguments so I know when your arguments stop and end. Please use colors in the following format: [R,G,B,Opacity]",false);
            help.setFooter("I am a bot beep boop");
            event.getChannel().sendMessage(help.build()).reference(event.getMessage()).queue();
            help.clear();
        }
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "helpall")) {
            statTrack(ProbieBot.prefix+"helpall");
            event.getChannel().sendTyping().queue();
            EmbedBuilder help = new EmbedBuilder();
            help.setColor(Color.RED.brighter());
            help.setTitle("ProbieBot Help");
            help.addField("", "Here are a list of commands available for the bot, along with their descriptions:", false);
            help.addField(ProbieBot.prefix+"info","This will give all the information public about me.",false);
            help.addField(ProbieBot.prefix+"help", "This will give you all of my available commands to serve you useless humans... I mean, my wonderful users:eyes:", false);
            help.addField(ProbieBot.prefix+"roast [@]","This command will roast whoever you ping. Use at user discretion.",false);
            help.addField(ProbieBot.prefix+"whois [@]","This will tell you the description of the person you pinged. These descriptions are written by the person.",false);
            help.addField(ProbieBot.prefix+"addwhois [description]","This will add YOUR description. Only you can access this command for your description. Please use the square brackets for this command, so I know when your description starts and ends.",false);
            help.addField(ProbieBot.prefix+"editwhois [description]", "This will replace YOUR previous description with the one you type in. Only you can access this command for your description. Please use the square brackets for this command, so I know when your description starts and ends.",false);
            help.addField(ProbieBot.prefix+"deletewhois","This will delete YOUR description that I have saved for you. Only you can access this command for your description.",false);
            help.addField(ProbieBot.prefix+"addquote","If you reply to a message with this command, it will add it to the quote list.",false);
            help.addField(ProbieBot.prefix+"deletequote [quote num]","This will delete a quote given the quote number; however, only the user who quoted the person and the person who is quoted may delete the quote in question.",false);
            help.addField(ProbieBot.prefix+"quotes [@]","This will give all quotes saved by the user pinged.",false);
            help.addField(ProbieBot.prefix+"viewquotes [quote # start] [quote # end]", "This will give you all quote numbers in between, and including, the numbers given by the arguments.",false);
            help.addField(ProbieBot.prefix+"embedtomsg","(NOT YET IMPLEMENTED)If you reply to a message with this command, it will extract the contents out of the embedded message",false);
            help.addField(ProbieBot.prefix+"msgtoembed","(NOT YET IMPLEMENTED)If you reply to a message with this command, it will put the contents in an embedded message",false);
            help.addField(ProbieBot.prefix+"createembed [title] [color] [subtitle 1] [msg 1] [subtitle 2] [msg 2]...","(NOT YET IMPLEMENTED)This command will create the embedded message with the given constraints. Please use square brackets for arguments so I know when your arguments stop and end. Please use colors in the following format: [R,G,B,Opacity]",false);
            help.addField(ProbieBot.prefix+"tarotqueue","This is a feature that will view the queue of people for tarot card readings.", false);
            help.addField(ProbieBot.prefix+"addtarotqueue","This is a feature that will add you to the queue of people for tarot card readings.",false);
            help.addField(ProbieBot.prefix+"deletetarotqueue","This is a feature that will delete you from the queue of people for tarot card readings.",false);
            help.addField(ProbieBot.prefix+"nexttarotqueue","RESERVED FOR MEAGAN: This is a feature that will pop off the first person in the queue of people for tarot card readings.",false);
            help.addField(ProbieBot.prefix+"weather [City, ST]", "This will give you the weather in a certain area. Please use the format: city, ST (abbreviation for state) *Currently only works in the US.", false);
            help.addField(ProbieBot.prefix+"gas [City, ST]", "This will give you the prices of Regular gas in a certain area. Please use the format: city, ST (abbreviation for state) *Currently only works in the US. **Currently only works for Regular gas", false);
            help.addField(ProbieBot.prefix+"suggest Message", "This will add a suggestion for the bot. Please use this wisely, as it does DM my owner. No need for square brackets or anything.", false);
            help.addField(ProbieBot.prefix+"addbday", "This will add your birthday to the list of birthdays to be announced. Please make sure it is correct before adding it, as you cannot change it once it is in. If it needs to be changed, please let my owner know and he can delete it manually.", false);
            help.addField(ProbieBot.prefix+"code", "This will DM you my source code (edited to make sure you hooligans don't steal my UAuth Key...)", false);
            help.addField(ProbieBot.prefix+"puppyme", "This will send you a random picture of a puppy.", false);
            help.setFooter("I am a bot beep boop");
            event.getAuthor().openPrivateChannel().complete().sendMessage(help.build()).queue();
            help.clear();
            help.setColor(Color.RED);
            help.setTitle("Help Page 2");
            help.addField(ProbieBot.prefix+"anagram Message", "This will send you an anagram of the text after the initial command. Currently the max number of words is 3.", false);
            help.addField(ProbieBot.prefix+"flman [date or no-date]", "This will send you either the most recent Florida Man article, or on a specific date given by the user in the format MM/DD.", false);
            help.addField(ProbieBot.prefix+"radar [zip code or no args]", "If given no arguments, it will send a radar image of the entire United States. If given a zip code (5 digit format), it will return a radar image of that area.", false);
            help.addField(ProbieBot.prefix+"latex [color or no color]", "If you want expression to be colored, put a comma after the color, then LaTeX code behind it. Default is white.", false);
            help.setFooter("I am a bot beep boop");
            event.getAuthor().openPrivateChannel().complete().sendMessage(help.build()).queue();
            help.clear();
            help.setColor(Color.RED.brighter());
            help.setDescription("I'm in your DM's" + event.getJDA().getGuildById("799255831190831115").getEmotesByName("NoblestOfNobles", true).get(0).getAsMention());
            event.getChannel().sendMessage(help.build()).reference(event.getMessage()).queue();
            help.clear();
        }
        // Roast
        else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "roast")){
            statTrack(ProbieBot.prefix+"roast");
            // How to get messages by message ID
            //System.out.println(event.getChannel().retrieveMessageById(args[1]).complete().getContentRaw());
            EmbedBuilder roast = new EmbedBuilder();
            roast.setColor(Color.RED.brighter());
            User roaste = null;
            
            if(args.length != 2){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format:\n"
                                             + "`%roast [@mention]`").reference(event.getMessage()).queue();
            }
            else if(args[1].startsWith("<@") && args[1].endsWith(">")){
                event.getChannel().sendTyping().queue();
                String user = args[1].substring(2, args[1].length()-1);
                roaste = ProbieBot.jda.retrieveUserById(user).complete();
            }
            else{
                throw new IllegalArgumentException("Location: Roast, incorrect formatting for user.");
            }
            // Added roasts
            if(roaste != null){
                Random rand = new Random();
                roast.setDescription(ProbieBot.roasts.get(rand.nextInt(ProbieBot.roasts.size())).replaceAll("((user))", roaste.getAsMention()).replaceAll("(ping User)", roaste.getAsMention()).replace('(', ' ').replace(')', ' '));
                event.getChannel().sendMessage(roast.build()).reference(event.getMessage()).queue();
                roast.clear();
            }
        }
        // Who is
         else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "whois")){
             statTrack(ProbieBot.prefix+"whois");
             EmbedBuilder description = new EmbedBuilder();
            description.setColor(Color.RED.brighter());
            User whois = null;
            String user = null;
            if(args.length != 2){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format:\n"
                                             + "`%whois [@mention]`").reference(event.getMessage()).queue();
            }
            else if(args[1].startsWith("<@") && args[1].endsWith(">")){
                event.getChannel().sendTyping().queue();
                user = args[1].substring(2, args[1].length()-1);
                whois = ProbieBot.jda.retrieveUserById(user).complete();
            }
            else{
                throw new IllegalArgumentException("Location: Whois, incorrect formatting for user.");
            }
            Scanner scan = null;
            while(scan==null){
                try{
                    scan = new Scanner(new File("whois.who"));
                }
                catch(FileNotFoundException e){
                    new File("whois.who").createNewFile();
                }
            }
            boolean found = false;
            String line = null;
            Scanner lineScan = null;
            while(scan.hasNext()){
                line = scan.nextLine();
                lineScan = new Scanner(line);
                if(lineScan.nextLong()==Long.parseLong(user)){
                    found = true;
                }
            }
            if(found){
                line = line.replace(user+" ", "");
                description.addField(whois.getName() + "'s description",line,false);
                event.getChannel().sendMessage(description.build()).reference(event.getMessage()).queue();
                description.clear();
            }
            else{
                event.getChannel().sendMessage("I could not find this user in my file. Ask them to add a description of themselves.").reference(event.getMessage()).queue();
            }
         }
         // Add Who is
         else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "addwhois")){
             statTrack(ProbieBot.prefix+"addwhois");
             EmbedBuilder description = new EmbedBuilder();
            description.setColor(Color.RED.brighter());
            User whois = null;
            String user = null;
            if(args.length == 1){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format:\n"
                                             + "`%addwhois [Any text]`").reference(event.getMessage()).queue();
            }
            else if(args[1].startsWith("[") && args[args.length-1].endsWith("]")){
                event.getChannel().sendTyping().queue();
                user = event.getAuthor().getId();
                whois = ProbieBot.jda.retrieveUserById(user).complete();
            }
            else{
                throw new IllegalArgumentException("Location: Addwhois, incorrect formatting for user.");
            }
            if(user!=null){
            Scanner scan = null;
            while(scan==null){
                try{
                    scan = new Scanner(new File("whois.who"));
                }
                catch(FileNotFoundException e){
                    new File("whois.who").createNewFile();
                }
            }
            boolean found = false;
            String line = null;
            Scanner lineScan = null;
            while(scan.hasNext()){
                line = scan.nextLine();
                lineScan = new Scanner(line);
                if(lineScan.nextLong()==Long.parseLong(user)){
                    found = true;
                }
            }
            if(found){
                event.getChannel().sendMessage("This user has already been defined in my file. Please use the command " + ProbieBot.prefix + "editwhois to change the description.").reference(event.getMessage()).queue();
            }
            else{
                FileWriter toFile=null;
                StringBuilder sb = new StringBuilder();
                try{
                    toFile = new FileWriter("whois.who",true);
                    BufferedWriter out = new BufferedWriter(toFile);
                    sb.append(user);
                    sb.append(" ");
                    sb.append(args[1].substring(1,args[1].length()));
                    sb.append(" ");
                    for (int i = 2; i < args.length-1; i++) {
                        sb.append(args[i]);
                        sb.append(" ");
                    }
                    sb.append(args[args.length-1].substring(0,args[args.length-1].length()-1));
                    out.write(sb.toString()+"\n");
                    out.close();
                    toFile.close();
                }
                catch(FileNotFoundException ex){
                    new File("whois.who").createNewFile();
                    toFile = new FileWriter("whois.who",true);
                    BufferedWriter out = new BufferedWriter(toFile);
                    sb.append(user);
                    sb.append(" ");
                    sb.append(args[1].substring(1,args[1].length()));
                    sb.append(" ");
                    for (int i = 2; i < args.length-1; i++) {
                        sb.append(args[i]);
                        sb.append(" ");
                    }
                    sb.append(args[args.length-1].substring(0,args[args.length-1].length()-1));
                    out.write(sb.toString()+"\n");
                    out.close();
                    toFile.close();
                }
                description.setDescription("The description for " + whois.getName() + " has been added! Here is what I got: " + sb.toString());
                event.getChannel().sendMessage(description.build()).reference(event.getMessage()).queue();
                description.clear();
            }
         }
         }
         // Edit Who is
         else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "editwhois")){
             statTrack(ProbieBot.prefix+"editwhois");
            EmbedBuilder description = new EmbedBuilder();
            description.setColor(Color.RED.brighter());
            User whois = null;
            String user = null;
            if(args.length == 1){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format:\n"
                                             + "`%editwhois [Any Text]`").reference(event.getMessage()).queue();
            }
            else if(args[1].startsWith("[") && args[args.length-1].endsWith("]")){
                event.getChannel().sendTyping().queue();
                user = event.getAuthor().getId();
                whois = ProbieBot.jda.retrieveUserById(user).complete();
            }
            else{
                throw new IllegalArgumentException("Location: Editwhois, incorrect formatting for user.");
            }
            Scanner scan = null;
            while(scan==null){
                try{
                    scan = new Scanner(new File("whois.who"));
                }
                catch(FileNotFoundException e){
                    new File("whois.who").createNewFile();
                }
            }
            boolean found = false;
            String line = null;
            Scanner lineScan = null;
            StringBuilder sb = new StringBuilder();
            while(scan.hasNext()){
                String data = scan.nextLine();
                line = data;
                lineScan = new Scanner(data);
                if(lineScan.nextLong()==Long.parseLong(user)){
                    found = true;
                }
                else{
                    sb.append(data);
                    sb.append("\n");
                }
            }
            if(found){
            }
            else{
                event.getChannel().sendMessage("I could not find you in my file. Add a description of yourself using the " + ProbieBot.prefix + "addwhois command.").reference(event.getMessage()).queue();
            }
            StringBuilder editedDes = new StringBuilder();
            new File("whois.who").delete();
            new File("whois.who").createNewFile();
                    PrintWriter toFile = new PrintWriter("whois.who");
                    editedDes.append(user);
                    editedDes.append(" ");
                    editedDes.append(args[1].substring(1,args[1].length()));
                    editedDes.append(" ");
                    for (int i = 2; i < args.length-1; i++) {
                        editedDes.append(args[i]);
                        editedDes.append(" ");
                    }
                    editedDes.append(args[args.length-1].substring(0,args[args.length-1].length()-1));
                    sb.append(editedDes.toString());
                    toFile.write(sb.toString()+"\n");
                    toFile.close();
                description.setDescription("The description for " + whois.getName() + " has been edited! Here is what I got: " + editedDes.toString());
                event.getChannel().sendMessage(description.build()).reference(event.getMessage()).queue();
         }
        // Delete Who is
         else if (args[0].equalsIgnoreCase(ProbieBot.prefix + "deletewhois")){
             statTrack(ProbieBot.prefix+"deletewhois");
              EmbedBuilder description = new EmbedBuilder();
            description.setColor(Color.RED.brighter());
            User whois = null;
            String user = null;
            if(args.length != 1){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format:\n"
                                             + "`%deletewhois`").reference(event.getMessage()).queue();
            }
            else if(args.length == 1){
                event.getChannel().sendTyping().queue();
                user = event.getAuthor().getId();
                whois = ProbieBot.jda.retrieveUserById(user).complete();
            }
            else{
                throw new IllegalArgumentException("Location: Deletewhois, incorrect formatting for user.");
            }
            Scanner scan = null;
            while(scan==null){
                try{
                    scan = new Scanner(new File("whois.who"));
                }
                catch(FileNotFoundException e){
                    new File("whois.who").createNewFile();
                }
            }
            boolean found = false;
            String line = null;
            Scanner lineScan = null;
            StringBuilder sb = new StringBuilder();
            while(scan.hasNext()){
                String data = scan.nextLine();
                line = data;
                lineScan = new Scanner(data);
                if(lineScan.nextLong()==Long.parseLong(user)){
                    found = true;
                }
                else{
                    sb.append(data);
                    sb.append("\n");
                }
            }
            if(found){
            }
            else{
                event.getChannel().sendMessage("I could not find you in my file.").reference(event.getMessage()).queue();
            }
            new File("whois.who").delete();
            new File("whois.who").createNewFile();
                    PrintWriter toFile = new PrintWriter("whois.who");
                    toFile.write(sb.toString());
                    toFile.close();
                description.setDescription("The description for " + whois.getName() + " has been deleted!");
                event.getChannel().sendMessage(description.build()).reference(event.getMessage()).queue();
                description.clear();
         }
         // List who is
         else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "listwhois")){
             
         }
        // Add Quote
         else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "addquote")){
             statTrack(ProbieBot.prefix+"addquote");
            EmbedBuilder quote = new EmbedBuilder();
            User quoter=null, quotee=null;
            String userQuoter=null, userQuotee=null;
            if(args.length ==1 && event.getMessage().getReferencedMessage()!=null){

                event.getChannel().sendTyping().queue();
                quoter = event.getAuthor();
                userQuoter = quoter.getId();
                quotee = event.getMessage().getReferencedMessage().getAuthor();
                userQuotee = quotee.getId();
                
                FileWriter toFile=null;
                StringBuilder sb = new StringBuilder();
                try{
                    Scanner fromFile = new Scanner(new File("quoteList.quote"));
                    Scanner lineScanner =null;
                    int quoteNumber = 0;
                    while(fromFile.hasNext()){
                        lineScanner = new Scanner(fromFile.nextLine());
                        quoteNumber = lineScanner.nextInt();
                    }
                    quoteNumber+=1;
                    toFile = new FileWriter("quoteList.quote",true);
                    BufferedWriter out = new BufferedWriter(toFile);
                    sb.append(quoteNumber);
                    sb.append(" ");
                    sb.append(userQuotee);
                    sb.append(" ");
                    sb.append(userQuoter);
                    sb.append(" ");
                    if(event.getMessage().getReferencedMessage().getEmbeds().isEmpty()){
                        sb.append(event.getMessage().getReferencedMessage().getContentRaw());
                    }
                    else{
                        sb.append(embedToString(event.getMessage().getReferencedMessage().getEmbeds()));
                    }
                    out.write(sb.toString()+"\n");
                    out.close();
                    toFile.close();
                }
                catch(FileNotFoundException ex){
                    new File("quoteList.quote").createNewFile();
                    Scanner fromFile = new Scanner(new File("quoteList.quote"));
                    Scanner lineScanner =null;
                    int quoteNumber = 1;
                    while(fromFile.hasNext()){
                        lineScanner = new Scanner(fromFile.nextLine());
                        quoteNumber = lineScanner.nextInt();
                    }
                    quoteNumber+=1;
                    toFile = new FileWriter("quoteList.quote",true);
                    BufferedWriter out = new BufferedWriter(toFile);
                    sb.append(quoteNumber);
                    sb.append(" ");
                    sb.append(userQuotee);
                    sb.append(" ");
                    sb.append(userQuoter);
                    sb.append(" ");
                    if(event.getMessage().getReferencedMessage().getEmbeds().isEmpty()){
                        sb.append(event.getMessage().getReferencedMessage().getContentRaw());
                    }
                    else{
                        sb.append(embedToString(event.getMessage().getReferencedMessage().getEmbeds()));
                    }
                    out.write(sb.toString()+"\n");
                    out.close();
                    toFile.close();
                }
                quote.setColor(Color.GREEN);
                quote.setDescription("Quote has been added. Here is what I have: "+sb.toString());
                event.getChannel().sendMessage(quote.build()).reference(event.getMessage()).queue();
                quote.clear();
            }
            else{
                throw new IllegalArgumentException("Location: Addquote, incorrect formatting for user.");
            }
         }
         // Delete Quote
         else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "deletequote")){
             statTrack(ProbieBot.prefix+"deletequote");
             User deleter = event.getAuthor();
             String user=deleter.getId();
             if(args.length==2){
                event.getChannel().sendTyping().queue();
                 try{
                     int quoteNumber = Integer.parseInt(args[1]);
                     Scanner find = null;
                     boolean found = false, canDelete=false;
                     try{
                        find = new Scanner(new File("quoteList.quote"));
                        while(find.hasNext()){
                            if(find.nextInt() == quoteNumber){
                                found = true;
                                String temp1 = find.next();
                                String temp2 = find.next();
                                if(temp1.equals(user) || temp2.equals(user)){
                                    canDelete = true;
                                }
                            }
                            find.nextLine();
                        }
                     }
                     catch(FileNotFoundException exe){
                         event.getChannel().sendMessage("There are no quotes to be deleted.").queue();
                     }
                     if(found && canDelete){
                         find = new Scanner(new File("quoteList.quote"));
                         StringBuilder sb = new StringBuilder();
                         int i = 1;
                         while(find.hasNext()){
                             if(quoteNumber == find.nextInt()){
                                 //Skip
                                 find.nextLine();
                             }
                             else{
                                 sb.append(i++);
                                 sb.append(" ");
                                 sb.append(find.nextLine());
                                 sb.append("\n");
                             }
                         }
                         new File("quoteList.quote").delete();
                        new File("quoteList.quote").createNewFile();
                        PrintWriter toFile = new PrintWriter("quoteList.quote");
                        toFile.write(sb.toString());
                        toFile.close();
                        EmbedBuilder delete = new EmbedBuilder();
                        delete.appendDescription("Quote has been deleted successfully.");
                        delete.setColor(Color.GREEN);
                        event.getChannel().sendMessage(delete.build()).reference(event.getMessage()).queue();
                        delete.clear();
                     }
                     else if(found && !canDelete){
                         // Throw error saying only quoter and quotee can delete the quote 
                         EmbedBuilder delete = new EmbedBuilder();
                         delete.setColor(Color.RED);
                        delete.appendDescription("Only quoter and quotee can delete the quote.");
                        event.getChannel().sendMessage(delete.build()).reference(event.getMessage()).queue();
                        delete.clear();
                     }
                     else{
                         // Throw error saying that the quote number could not be found.
                         EmbedBuilder delete = new EmbedBuilder();
                         delete.setColor(Color.RED);
                        delete.appendDescription("The quote number could not be found.");
                        event.getChannel().sendMessage(delete.build()).reference(event.getMessage()).queue();
                        delete.clear();
                     }
                 }
                 catch (NumberFormatException e){
                     event.getMessage().addReaction("❌").queue();
                 }
             }
             else{
                 throw new IllegalArgumentException("Location: Deletequote, incorrect formatting for user.");
             }
         }
         // Quotes
         else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "quotes")){
             statTrack(ProbieBot.prefix+"quotes");
             if(args.length==2){
                String user = null;
                User whois = null;
                if(args[1].startsWith("<@") && args[1].endsWith(">")){
                    event.getChannel().sendTyping().queue();
                    user = args[1].substring(2, args[1].length()-1);
                    whois = ProbieBot.jda.retrieveUserById(user).complete();
                    Scanner find = new Scanner(new File("quoteList.quote"));
                    StringBuilder sb = new StringBuilder();
                    while(find.hasNext()){
                        String data = find.nextLine();
                        Scanner scan = new Scanner(data);
                        scan.nextInt();
                        if(scan.next().equals(user)){
                            Scanner scan2 = new Scanner(data);
                            int quoteNumber = scan2.nextInt();
                            String quotee = scan2.next();
                            String quoter = scan2.next();
                            String quote = scan2.nextLine();
                            quote = quote.trim();
                            sb.append("**Quote #").append(quoteNumber).append("**: \"");
                            sb.append(quote);
                            sb.append("\" ~ ");
                            sb.append(event.getGuild().retrieveMemberById(quotee).complete().getNickname());
                            sb.append(" (Quoted by ");
                            sb.append(event.getGuild().retrieveMemberById(quoter).complete().getNickname());
                            sb.append(")\n");
                        }
                    }
                    EmbedBuilder quotes = new EmbedBuilder();
                    quotes.setTitle("Quotes by " + event.getGuild().retrieveMemberById(user).complete().getNickname());
                    quotes.setDescription(sb.toString().trim());
                    quotes.setColor(Color.GREEN);
                    event.getChannel().sendMessage(quotes.build()).reference(event.getMessage()).queue();
                }
                else{
                    throw new IllegalArgumentException("Location: quotes, incorrect formatting for user.");
                }
             }
             else{
                 throw new IllegalArgumentException("Location: quotes, incorrect formatting for user.");
             }
         }
         // List Quotes
         else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "listquotes")){
             statTrack(ProbieBot.prefix+"listquotes");
            if (args.length == 2 || args.length == 3){
                event.getChannel().sendTyping().queue();
                if(args.length ==2){
                    try{
                        int reqQuote = Integer.parseInt(args[1]);
                        if(reqQuote < 0){
                            throw new NumberFormatException();
                        }
                        Scanner find = new Scanner(new File("quoteList.quote"));
                        int highestQuoteNumber = 0;
                        while(find.hasNext()){
                            highestQuoteNumber = find.nextInt();
                            find.nextLine();
                        }
                        if(reqQuote > highestQuoteNumber){
                            throw new NumberFormatException();
                        }
                        find = new Scanner(new File("quoteList.quote"));
                        StringBuilder sb = new StringBuilder();
                        while(find.hasNext()){
                            int num = find.nextInt();
                            if(num==reqQuote){
                                String data = find.nextLine();
                                data = num+data;
                                Scanner scan = new Scanner(data);
                                scan.nextInt();
                                Scanner scan2 = new Scanner(data);
                                int quoteNumber = scan2.nextInt();
                                String quotee = scan2.next();
                                String quoter = scan2.next();
                                String quote = scan2.nextLine();
                                quote = quote.trim();
                                sb.append("**Quote #").append(quoteNumber).append("**: \"");
                                sb.append(quote);
                                sb.append("\" ~ ");
                                sb.append(event.getGuild().retrieveMemberById(quotee).complete().getNickname());
                                sb.append(" (Quoted by ");
                                sb.append(event.getGuild().retrieveMemberById(quoter).complete().getNickname());
                                sb.append(")\n");
                            }
                            else{
                                find.nextLine();
                            }
                        }
                        EmbedBuilder quotes = new EmbedBuilder();
                        quotes.setTitle("Quote " + reqQuote);
                        quotes.setDescription(sb.toString().trim());
                        quotes.setColor(Color.GREEN);
                        event.getChannel().sendMessage(quotes.build()).reference(event.getMessage()).queue();
                    }
                    catch (NumberFormatException e){
                        event.getMessage().addReaction("❌").queue();
                    }
                }
                else{
                    try{
                        int lowerLimit = Integer.parseInt(args[1]);
                        int upperLimit = Integer.parseInt(args[2]);
                        if(lowerLimit < 0){
                            throw new NumberFormatException();
                        }
                        Scanner find = new Scanner(new File("quoteList.quote"));
                        int highestQuoteNumber = 0;
                        while(find.hasNext()){
                            highestQuoteNumber = find.nextInt();
                            find.nextLine();
                        }
                        if(upperLimit > highestQuoteNumber){
                            upperLimit = highestQuoteNumber;
                        }
                        find = new Scanner(new File("quoteList.quote"));
                        StringBuilder sb = new StringBuilder();
                        while(find.hasNext()){
                            int num = find.nextInt();
                            if(num>=lowerLimit && num<=upperLimit){
                                String data = find.nextLine();
                                data = num+data;
                                Scanner scan = new Scanner(data);
                                scan.nextInt();
                                Scanner scan2 = new Scanner(data);
                                int quoteNumber = scan2.nextInt();
                                String quotee = scan2.next();
                                String quoter = scan2.next();
                                String quote = scan2.nextLine();
                                quote = quote.trim();
                                sb.append("**Quote #").append(quoteNumber).append("**: \"");
                                sb.append(quote);
                                sb.append("\" ~ ");
                                sb.append(event.getGuild().retrieveMemberById(quotee).complete().getNickname());
                                sb.append(" (Quoted by ");
                                sb.append(event.getGuild().retrieveMemberById(quoter).complete().getNickname());
                                sb.append(")\n");
                            }
                            else{
                                find.nextLine();
                            }
                        }
                        EmbedBuilder quotes = new EmbedBuilder();
                        quotes.setTitle("Quotes " + lowerLimit + "-" + upperLimit);
                        quotes.setDescription(sb.toString().trim());
                        quotes.setColor(Color.GREEN);
                        event.getChannel().sendMessage(quotes.build()).reference(event.getMessage()).queue();
                    }
                    catch (NumberFormatException e){
                        event.getMessage().addReaction("❌").queue();
                    }
                }
            }
         }
         //Tarot queue
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "tarotqueue")){
            statTrack(ProbieBot.prefix+"tarotqueue");
            event.getChannel().sendTyping().queue();
            EmbedBuilder queue = new EmbedBuilder();
            queue.setColor(Color.BLUE.brighter());
            StringBuilder sb = new StringBuilder();
            User[] temp = new User[tarotQueue.size()];
            for (int i = 0; i < tarotQueue.size(); i++) {
                temp[i] = tarotQueue.remove();
                tarotQueue.add(temp[i]);
            }
            for (int i = 0; i < temp.length; i++) {
                sb.append(i+1);
                sb.append(". ");
                sb.append(temp[i].getName());
                if(i!=tarotQueue.size())
                    sb.append("\n");
            }
            if(sb.toString().isEmpty()){
                queue.setDescription("Queue is empty.");
            }
            else{
                queue.setDescription(sb.toString());
            }
            event.getChannel().sendMessage(queue.build()).reference(event.getMessage()).queue();
            queue.clear();
        }
        // Add to Tarot queue
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "addtarotqueue")){
            statTrack(ProbieBot.prefix+"addtarotqueue");
            event.getChannel().sendTyping().queue();
            if(!tarotQueue.contains(event.getAuthor()))
                tarotQueue.add(event.getAuthor());
            if(tarotQueue.contains(event.getAuthor())){
                event.getMessage().addReaction("✅").queue();
            }
            else{
                event.getMessage().addReaction("❌").queue();
            }
        }
        //Remove from Tarot queue
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "deletetarotqueue")){
            statTrack(ProbieBot.prefix+"deletetarotqueue");
            event.getChannel().sendTyping().queue();
            if(tarotQueue.contains(event.getAuthor())){
                tarotQueue.remove(event.getAuthor());
                event.getMessage().addReaction("✅").queue();
            }
            else{
                event.getMessage().addReaction("❌").queue();
            }
        }
        //Pop off Tarot queue
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "nexttarotqueue")){
            if(event.getAuthor().equals(event.getGuild().retrieveMemberById("381219812648550412").complete().getUser())){
                tarotQueue.remove();
                EmbedBuilder queue = new EmbedBuilder();
                StringBuilder sb = new StringBuilder();
                User[] temp = new User[tarotQueue.size()];
                for (int i = 0; i < tarotQueue.size(); i++) {
                    temp[i] = tarotQueue.remove();
                    tarotQueue.add(temp[i]);
                }
                for (int i = 0; i < temp.length; i++) {
                    sb.append(i+1);
                    sb.append(". ");
                    sb.append(temp[i].getName());
                    if(i!=tarotQueue.size())
                        sb.append("\n");
                }
                if(sb.toString().isEmpty()){
                    queue.setDescription("Queue is empty. Good job Meagan");
                }
                else{
                    queue.setDescription(sb.toString());
                }
                queue.setColor(Color.GREEN);
                event.getChannel().sendMessage(queue.build()).reference(event.getMessage()).queue();
                queue.clear();
            }
            else{
                event.getMessage().addReaction("❌").queue();
            }
        }
        // Weather
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "weather")){
            if(args.length< 3){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format:\n"
                                             + "`%weather City, ST`\n (There must be a space after comma)").reference(event.getMessage()).queue();
            }
            else{
                statTrack(ProbieBot.prefix+"weather");
                event.getChannel().sendTyping().queue();
                StringBuilder sb = new StringBuilder();
                int i = 0;
                do{
                    i++;
                    sb.append(args[i]);
                    sb.append(" ");

                }
                while(!args[i].contains(","));
                String city = sb.toString().substring(0, sb.toString().length()-2);
                String state = args[i+1];
                try{
                    event.getChannel().sendMessage(weatherScrape(("https://www.wunderground.com/weather/us/"+state+"/"+city.replaceAll(" ", "+")).toLowerCase()).build()).reference(event.getMessage()).queue();
                }
                catch(Exception ex){
                    event.getMessage().addReaction("❌").queue();
                }
            }
        }
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "gas")){
            if(args.length < 3){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format:\n"
                                             + "`%gas City, ST`\n (There must be a space after comma)").reference(event.getMessage()).queue();
            }
            else{
                statTrack(ProbieBot.prefix+"gas");
                event.getChannel().sendTyping().queue();
                try{
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    do{
                        i++;
                        sb.append(args[i]);
                        sb.append(" ");

                    }
                    while(!args[i].contains(","));
                    String city = sb.toString().substring(0, sb.toString().length()-2);
                    String state = args[i+1];
                    event.getChannel().sendMessage(gasScrape("https://www.gasbuddy.com/home?search="+city.replaceAll(" ", "+")+"+"+state+"&fuel=1").build()).reference(event.getMessage()).queue();
                }
                catch(Exception ex){
                    event.getMessage().addReaction("❌").queue();
                }
            }
        }
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "suggest")){
            statTrack(ProbieBot.prefix+"suggest");
            if(args.length==1){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format:\n"
                                             + "`%Suggest [Any Text]`").reference(event.getMessage()).queue();
            }
            else{
                event.getChannel().sendTyping().queue();
                StringBuilder sb = new StringBuilder();
                sb.append("From ");
                sb.append(event.getAuthor().getName());
                sb.append(" (");
                sb.append(event.getAuthor().getId());
                sb.append("): ");
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]);
                    sb.append(" ");
                }
                event.getGuild().retrieveMemberById("540957458944884746").complete().getUser().openPrivateChannel().complete().sendMessage(sb).queue();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Suggestion");
                embed.appendDescription("Suggestion has been sent!");
                embed.setColor(Color.GREEN);
                event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                embed.clear();
            }
        }
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "addbday")){
            statTrack(ProbieBot.prefix+"addbday");
            event.getChannel().sendTyping().queue();
            boolean correctFormat = true;
            try{
                Integer.parseInt(args[1].substring(0,4));
                Integer.parseInt(args[1].substring(5,7));
                Integer.parseInt(args[1].substring(8,10));
            }
            catch (NumberFormatException ex){
                correctFormat = false;
            }
            if(args.length==1){
                event.getMessage().addReaction("❌").queue();
            }
            else if(!correctFormat){
                event.getMessage().addReaction("❌").queue();
            }
            else{
                if(!args[1].contains("/")){
                    event.getMessage().addReaction("❌").queue();
                }
                else if(args[1].length()!=10){
                    event.getMessage().addReaction("❌").queue();
                }
                else{
                    Scanner scan = null;
                    try{
                        scan = new Scanner(new File("birthday.birth"));
                    }
                    catch(FileNotFoundException ex){
                        try{
                            new File("birthday.birth").createNewFile();
                            scan = new Scanner(new File("birthday.birth"));
                        }
                        catch(IOException exe){}
                    }
                    FileWriter toFile = new FileWriter("birthday.birth",true);
                    BufferedWriter out = new BufferedWriter(toFile);
                    boolean canContinue = true;
                    while(scan.hasNext() && canContinue){
                        scan.next();
                        if(scan.next().equals(event.getAuthor().getId())){
                            event.getChannel().sendMessage("You cannot add your birthday, because it has already been added. Contact my owner if needed to change.").reference(event.getMessage()).queue();
                            canContinue = false;
                        }
                    }
                    if(canContinue){
                        out.append(args[1]);
                        out.append(" ");
                        out.append(event.getAuthor().getId());
                        out.append("\n");
                        out.close();
                        event.getChannel().sendMessage("Birthday has been added!").reference(event.getMessage()).queue();
                    }
                }
            }
        }
        // Florida Man
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "flman")){
            statTrack(ProbieBot.prefix+"flman");
            event.getChannel().sendTyping().queue();
            if(args.length>3){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format(s):\n"
                                             + "`%flman MM/DD` for an article on a certain day\n"
                                             + "`%flman` for most recent article").reference(event.getMessage()).queue();
            }
            else if(args.length == 2){
                try{
                    Integer.parseInt(args[1].substring(0,2));
                    Integer.parseInt(args[1].substring(3,5));
                    event.getChannel().sendMessage(flmanScrape("https://www.google.com/search?q=florida+man+"+args[1]+"&tbm=nws")).reference(event.getMessage()).queue();
                }
                catch(NumberFormatException e){
                    event.getMessage().addReaction("❌").queue();
                }
            }
            else if(args.length == 1){
                event.getChannel().sendMessage(flmanScrape("https://www.google.com/search?q=florida+man+most+recent&tbm=nws")).reference(event.getMessage()).queue();
            }
        }
        // Source code
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "code")){
            statTrack(ProbieBot.prefix+"code");
            event.getChannel().sendTyping().queue();
            event.getAuthor().openPrivateChannel().complete().sendFile(new File("ProbieBotPublic.java")).queue();
            event.getAuthor().openPrivateChannel().complete().sendFile(new File("Commands.java")).queue();
            event.getAuthor().openPrivateChannel().complete().sendFile(new File("GasPrice.java")).queue();
            event.getAuthor().openPrivateChannel().complete().sendFile(new File("LetterCount.java")).queue();
            event.getAuthor().openPrivateChannel().complete().sendFile(new File("Calculator.java")).queue();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.GREEN);
            embed.setDescription("I'm in your DM's" + event.getGuild().getEmotesByName("NoblestOfNobles", true).get(0).getAsMention());
            event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
            embed.clear();
        }
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "puppyme")){
            statTrack(ProbieBot.prefix+"puppyme");
            event.getChannel().sendTyping().queue();
            try{
                event.getChannel().sendMessage(puppyScrape("https://www.generatormix.com/random-dog-generator?number=1")).reference(event.getMessage()).queue();
            }
            catch(Exception ex){
                event.getMessage().addReaction("❌").queue();
            }
        }
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "anagram")){
            statTrack(ProbieBot.prefix+"anagram");
            event.getChannel().sendTyping().queue();
            if(args.length>4 || args.length==1){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format(s):\n"
                                             + "`%anagram [Any text less than 4 words]`").reference(event.getMessage()).queue();
            }
            else{
                String words = event.getMessage().getContentRaw().substring(event.getMessage().getContentRaw().indexOf(" ")+1);
                event.getChannel().sendMessage(anagramScrape("https://wordsmith.org/anagram/anagram.cgi?anagram="+words.replaceAll(" ", "+"), words).build()).reference(event.getMessage()).queue();
            }
            
        }
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "deletemsg")){
            if(event.getAuthor().getId().equals("540957458944884746")){
                event.getMessage().getReferencedMessage().delete().complete();
            }
        }
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "radar")){
            statTrack(ProbieBot.prefix+"radar");
            event.getChannel().sendTyping().queue();
            if(args.length == 1){
                String URL = "https://radar.weather.gov/ridge/standard/CONUS_loop.gif";
                String name = "radar.gif";
                File gif = downloadFile(URL, name);
                event.getChannel().sendFile(gif).reference(event.getMessage()).queue();
                new File(name).delete();
            }
            else if(args.length == 2){
                try{
                    String zip = args[1];
                    String latlon = latLongScrape(zip);
                    String lat = latlon.substring(0,latlon.indexOf(","));
                    String lon = latlon.substring(latlon.indexOf(",")+1);
                    lon = lon.replaceAll(" ", "");
                    File gif = downloadFile(radarScrape(("https://forecast.weather.gov/MapClick.php?lat="+lat+"&lon="+lon).replaceAll(" ", "+")), "radar.gif");
                    event.getChannel().sendFile(gif).reference(event.getMessage()).queue();
                    new File("radar.gif").delete();
                    
                }
                catch(Exception ex){
                    event.getMessage().addReaction("❌").queue();
                }
            }
            else{
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format(s):\n"
                                             + "`%radar [zip code]` for an radar of a certain area\n"
                                             + "`%radar` for radar of CONUS").reference(event.getMessage()).queue();
            
            }
        }
        else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "latex")){
            statTrack(ProbieBot.prefix+"latex");
            event.getChannel().sendTyping().queue();
            // String: https://latex.codecogs.com/png.image?\tiny&space;\dpi{1000}\color{INSERT COLOR}LATEX
            // Example: https://latex.codecogs.com/png.image?\tiny&space;\dpi{1000}\color{white}&space;x&space;=&space;\frac{-b\pm\sqrt{b^2-4ac}}{2a}
            if (args.length==1){
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format(s):\n"
                                             + "`%latex color, [code]` for equation in color\n"
                                             + "`%latex [code]` for equation in white").reference(event.getMessage()).queue();
            
            }
            else{
                String URL = "https://latex.codecogs.com/png.image?\\tiny\\dpi{1000}\\color{LC}{LATEX}";
                if(args[1].equalsIgnoreCase("white,") || 
                        args[1].equalsIgnoreCase("black,") ||
                        args[1].equalsIgnoreCase("red,") ||
                        args[1].equalsIgnoreCase("green,") ||
                        args[1].equalsIgnoreCase("blue,") ||
                        args[1].equalsIgnoreCase("cyan,") ||
                        args[1].equalsIgnoreCase("magenta,") ||
                        args[1].equalsIgnoreCase("yellow,")){
                    String message = event.getMessage().getContentRaw();
                    message = message.substring(message.indexOf(",")+1);
                    String latex = (message.replaceAll(" ", "&space;")).replace("\n", "&space;");
                    URL = (URL.replace("{LC}", "{" + args[1].substring(0,args[1].length()-1) + "}")).replace("{LATEX}", latex);
                    if(latex.length()>1024){
                        event.getMessage().addReaction("❌").queue();
                        event.getChannel().sendMessage("LaTeX String is Too Long!");
                    }
                    else{
                        event.getChannel().sendMessage(URL).reference(event.getMessage()).queue();
                    }
                }
                else if(args[1].contains(",")){
                    event.getMessage().addReaction("❌").queue();
                    event.getChannel().sendMessage("Invalid color.").reference(event.getMessage()).queue();
            
                }
                else{ // Default white
                    String message = event.getMessage().getContentRaw();
                    message = message.substring(message.indexOf("%latex")+6);
                    String latex = (message.replaceAll(" ", "&space;")).replace("\n", "&space;");
                    while(latex.contains("&space;&space;")){
                        latex = latex.replaceAll("&space;&space;", "&space;");
                    }
                    URL = (URL.replace("{LC}", "{" + "white" + "}")).replace("{LATEX}", latex);
                    if(latex.length()>1024){
                        event.getMessage().addReaction("❌").queue();
                        event.getChannel().sendMessage("LaTeX String is Too Long!").reference(event.getMessage()).queue();
                    }
                    else{
                        event.getChannel().sendMessage(URL).reference(event.getMessage()).queue();
                    }
                }
            }
        }
        else if((args[0].equalsIgnoreCase(ProbieBot.prefix + "resistor"))){
            //statTrack(ProbieBot.prefix+"resistor");
            event.getChannel().sendTyping().queue();
            if(args.length<2){
                event.getMessage().addReaction("❌").queue();
            }
            else{
                EmbedBuilder embed = new EmbedBuilder();
                switch(args.length){
                    case 2:
                        break;
                    case 3:
                        boolean num = false;
                        for (int i = 0; i < args[1].length() && !num; i++) {
                            try{
                                Integer.parseInt(""+args[1].charAt(i));
                                num = true;
                            }
                            catch (NumberFormatException ex){}
                        }
                        int bandNum = 0;
                        if(num){
                            try{
                                bandNum = Integer.parseInt(args[2]);
                            }
                            catch(NumberFormatException ex){}
                        }
                        if(num && bandNum != 0){
                            switch(bandNum){
                                case 3:
                                    boolean isNum1 = true;
                                    String value1 = "";
                                    int index1 = 0;
                                    String multLet = null;
                                    while(isNum1 && index1 != args[1].length()){
                                        try{
                                            if(args[1].charAt(index1) == '.'){
                                                index1++;
                                                value1 += ".";
                                            }
                                            else{
                                                value1 += Integer.parseInt(""+args[1].charAt(index1++));
                                            }
                                        }
                                        catch (NumberFormatException ex){
                                            isNum1 = false;
                                            multLet = ""+args[1].charAt(index1-1);
                                        }                                        
                                    }
                                    double numValue = Double.parseDouble(value1);
                                    int timesMult = 0;
                                    boolean rounded = false;
                                    int timesDiv = 0;
                                    while(numValue > 10){
                                        numValue /= 10;
                                        timesDiv++;
                                    }
                                    while(!rounded){
                                        if(numValue <= 10){
                                            numValue *= 10;
                                            timesMult++;
                                        }
                                        else{
                                            numValue = Math.round(numValue);
                                            numValue /= (Math.pow(10, timesMult));
                                            rounded = true;
                                        }
                                    }
                                    numValue *= Math.pow(10,timesDiv);
                                    int multiplier = 0;
                                    if(multLet == null){
                                        multLet = "";
                                    }
                                    switch(multLet){
                                        case "":
                                            multiplier = 0;
                                            break;
                                        case "K":
                                        case "k":
                                            multiplier = 3;
                                            break;
                                        case "M":
                                        case "m":
                                            multiplier = 6;
                                            break;
                                        case "G":
                                        case "g":
                                            multiplier = 9;
                                            break;
                                        case "T":
                                        case "t":
                                            multiplier = 12;
                                            break;
                                        case "P":
                                        case "p":
                                            multiplier = 15;
                                            break;
                                        default:
                                            event.getMessage().addReaction("❌").queue();
                                            break;
                                    }
                                    int digit1 = -1, digit2=-1;
                                    value1 = "" + numValue;
                                    if((""+numValue).contains(".") && (""+numValue).length()>7){
                                        numValue = (int)numValue;
                                    }
                                    if(numValue != (int)numValue && numValue < 1){
                                        if(numValue < .1){
                                            digit1 = Integer.parseInt(""+value1.charAt(2));
                                            digit2 = Integer.parseInt(""+value1.charAt(3));
                                        }
                                        else if(numValue < 1){
                                            if(value1.length() == 4){
                                                digit2 = Integer.parseInt(""+value1.charAt(3));
                                            }
                                            else{
                                                digit2 = 0;
                                            }
                                            digit1 = Integer.parseInt(""+value1.charAt(2));
                                        }
                                    }
                                    else if(numValue != (int)numValue){
                                        if(numValue > 10 && multLet == ""){
                                            digit1 = Integer.parseInt(""+value1.charAt(0));
                                            digit2 = Integer.parseInt(""+value1.charAt(1));
                                        }
                                        else{
                                            digit1 = Integer.parseInt(""+value1.charAt(0));
                                            digit2 = Integer.parseInt(""+value1.charAt(2));
                                        }
                                        System.out.println(value1);
                                        System.out.println("if1");
                                    }
                                    else{
                                        if((""+(int)(numValue)).length()==1){
                                            System.out.println("elseif1");
                                            if(numValue < 10 && multLet == ""){
                                                digit1 = 0;
                                                digit2 = Integer.parseInt(""+value1.charAt(0));
                                            }
                                            else{
                                                digit1 = Integer.parseInt(""+value1.charAt(0));
                                                digit2 = 0;
                                            }
                                            
                                        }
                                        else{
                                            System.out.println("elseif2");
                                            digit1 = Integer.parseInt(""+value1.charAt(0));
                                            digit2 = Integer.parseInt(""+value1.charAt(1));
                                        }
                                    }
                                    //numValue *= Math.pow(10,multiplier);
                                    String color1 = Resistor.colors[digit1];
                                    String color2 = Resistor.colors[digit2];
                                    System.out.println(numValue);
                                    if(numValue < 0.1){
                                        numValue*=100;
                                        multiplier -= 2;
                                    }
                                    if(numValue < 1){
                                        if(multiplier == 0 && color1 != "black"){
                                            numValue *= 100;
                                            multiplier -= 2;
                                        }
                                        else{
                                            numValue *= 10;
                                            multiplier--;
                                        }
                                        if(numValue < 10){
                                            numValue *= 10;
                                            multiplier--;
                                        }
                                    }
                                    else if(numValue < 10){
                                        if(multiplier == 0 && color1 != "black"){
                                            numValue *= 10;
                                            multiplier--;
                                        }
                                    }
                                    else if(numValue < 100){
                                        numValue /=10;
                                        multiplier++;
                                    }
                                    else if(numValue < 1000){
                                        numValue /= 100;
                                        multiplier += 2;
                                    }
                                    System.out.println(multiplier);
                                    if(multiplier > 0){
                                        multiplier--;
                                    }
                                    String multColor = "";
                                    for (int i = 0; i < Resistor.colorsValue.length; i++) {
                                        if(Resistor.colorsValue[i] == multiplier){
                                            multColor = Resistor.colors[i];
                                        }
                                    }
                                    System.out.println(numValue);
                                    System.out.println(color1 + " " + color2 + " " + multColor);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("**Resistance:** ");
                                    sb.append(args[1] + "\n");
                                    sb.append("**3-Band Color Code:** ");
                                    sb.append(color1 + ", " + color2 + ", " + multColor);
                                    sb.append("\n");
                                    sb.append("**Default Tolerance:** ±20%");
                                    embed = new EmbedBuilder();
                                    embed.setColor(Color.green);
                                    embed.setDescription(sb.toString());
                                    event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    break;
                                case 6:
                                    break;
                                default:
                                    event.getMessage().addReaction("❌").queue();
                                    break;
                            }
                        }
                        else{
                            event.getMessage().addReaction("❌").queue();
                        }
                        break;
                    case 4:
                        boolean num1 = false;
                        for (int i = 0; i < args[1].length() && !num1; i++) {
                            try{
                                Integer.parseInt(""+args[1].charAt(i));
                                num = true;
                            }
                            catch (NumberFormatException ex){}
                        }
                        int bandNum1 = 0;
                        if(num1){
                            try{
                                bandNum1 = Integer.parseInt(args[2]);
                            }
                            catch(NumberFormatException ex){}
                        }
                        if(!num1){
                            String[] colors3 = {args[1],args[2],args[3]};
                            Resistor res1 = new Resistor(colors3);
                            StringBuilder sb1 = new StringBuilder();
                            sb1.append("Colors: " + args[1] +", "+ args[2] +", "+ args[3] + "\n");
                            double value1 = res1.resistanceCalc();
                            int index1 = 0;
                            while(value1>1000){
                                value1 /= 1000.0;
                                index1++;
                            }
                            String unit1 = "Ω";
                            switch(index1){
                                case 0:
                                    break;
                                case 1:
                                    unit1 = "K" + unit1;
                                    break;
                                case 2:
                                    unit1 = "M" + unit1;
                                     break;
                                case 3:
                                    unit1 = "G" + unit1;
                                    break;
                                case 4:
                                    unit1 = "T" + unit1;
                                    break;
                                case 5:
                                    unit1 = "P" + unit1;
                                    break;
                            }
                            sb1.append("Value: "+ value1 + unit1 + "\n");
                            sb1.append("Tolerance: ±" + res1.getTolerance()*100 + "%");
                            embed.setDescription(sb1.toString());
                            event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                            break;
                        }
                        else if(num1 && bandNum1 != 0){
                            switch(bandNum1){
                                case 3:
                                    boolean isNum1 = true;
                                    String value1 = "";
                                    int index1 = 0;
                                    while(isNum1){
                                        try{
                                            if(args[1].charAt(index1) == '.'){
                                                index1++;
                                                value1 += ".";
                                            }
                                            else{
                                                value1 += Integer.parseInt(""+args[1].charAt(index1++));
                                            }
                                        }
                                        catch (NumberFormatException ex){
                                            isNum1 = false;
                                        }
                                    }
                                    System.out.println(value1);
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    break;
                                case 6:
                                    break;
                                default:
                                    event.getMessage().addReaction("❌").queue();
                                    break;
                            }
                        }
                        else{
                            event.getMessage().addReaction("❌").queue();
                        }
                    case 5:
                        String[] colors4 = {args[1],args[2],args[3],args[4]};
                        Resistor res2 = new Resistor(colors4);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Colors: " + args[1] +", "+ args[2] +", "+ args[3] +", "+ args[4] + "\n");
                        double value2 = res2.resistanceCalc();
                        int index2 = 0;
                        while(value2>1000){
                            value2 /= 1000.0;
                            index2++;
                        }
                        String unit2 = "Ω";
                        switch(index2){
                            case 0:
                                break;
                            case 1:
                                unit2 = "K" + unit2;
                                break;
                            case 2:
                                unit2 = "M" + unit2;
                                 break;
                            case 3:
                                unit2 = "G" + unit2;
                                break;
                            case 4:
                                unit2 = "T" + unit2;
                                break;
                            case 5:
                                unit2 = "P" + unit2;
                                break;
                        }
                        sb2.append("Value: "+ value2 + unit2 + "\n");
                        sb2.append("Tolerance: ±" + res2.getTolerance()*100 + "%");
                        embed.setDescription(sb2.toString());
                        event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                        break;
                    case 6:
                        String[] colors5 = {args[1],args[2],args[3],args[4], args[5]};
                        Resistor res3 = new Resistor(colors5);
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Colors: " + args[1] +", "+ args[2] +", "+ args[3] +", " + args[4] +", "+ args[5] + "\n");
                        double value3 = res3.resistanceCalc();
                        int index3 = 0;
                        while(value3>1000){
                            value3 /= 1000.0;
                            index3++;
                        }
                        String unit3 = "Ω";
                        switch(index3){
                            case 0:
                                break;
                            case 1:
                                unit3 = "K" + unit3;
                                break;
                            case 2:
                                unit3 = "M" + unit3;
                                 break;
                            case 3:
                                unit3 = "G" + unit3;
                                break;
                            case 4:
                                unit3 = "T" + unit3;
                                break;
                            case 5:
                                unit3 = "P" + unit3;
                                break;
                        }
                        sb3.append("Value: "+ value3 + unit3 + "\n");
                        sb3.append("Tolerance: ±" + res3.getTolerance()*100 + "%");
                        embed.setDescription(sb3.toString());
                        event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                        break;
                    case 7:
                        String[] colors6 = {args[1],args[2],args[3],args[4], args[5], args[6]};
                        Resistor res4 = new Resistor(colors6);
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Colors: " + args[1] +", "+ args[2] +", "+ args[3] +", "+ args[4] +", "+args[5]+", "+args[6]+ "\n");
                        double value4 = res4.resistanceCalc();
                        int index4 = 0;
                        while(value4>1000){
                            value4 /= 1000.0;
                            index4++;
                        }
                        String unit4 = "Ω";
                        switch(index4){
                            case 0:
                                break;
                            case 1:
                                unit4 = "K" + unit4;
                                break;
                            case 2:
                                unit4 = "M" + unit4;
                                 break;
                            case 3:
                                unit4 = "G" + unit4;
                                break;
                            case 4:
                                unit4 = "T" + unit4;
                                break;
                            case 5:
                                unit4 = "P" + unit4;
                                break;
                        }
                        sb4.append("Value: "+ value4 + unit4 + "\n");
                        sb4.append("Tolerance: ±" + res4.getTolerance()*100 + "%\n");
                        sb4.append("Temperature Coefficient: " + res4.getTempCoeff() + "ppm/K");
                        embed.setDescription(sb4.toString());
                        event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                        break;
                }
            }
        }
        else if((args[0].equalsIgnoreCase(ProbieBot.prefix + "stats"))){
            statTrack(ProbieBot.prefix+"stats");
            event.getChannel().sendTyping().queue();
            if (args.length == 1){
                event.getChannel().sendTyping().queue();
                Scanner fromFile=null, test = null;
        PrintWriter toFile = null;
        try{
            fromFile = new Scanner(new File("stat.stat"));
        }
        catch(FileNotFoundException ex){
            try{
                new File("stat.stat").createNewFile();
                fromFile = new Scanner(new File("stat.stat"));
            }
            catch(FileNotFoundException e){}
            catch(IOException e){}
        }
        StringBuilder sb = new StringBuilder();
        while(fromFile.hasNext()){
            String temp = fromFile.next();
            sb.append("`"+temp+"`");
            sb.append(": ");
            sb.append(" "+fromFile.nextInt());
            sb.append("\n");
        }
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("All Stats");
                embed.setDescription(sb.toString());
                embed.setColor(Color.GREEN);
                embed.setFooter("I am a bot beep boop");
                event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
            }
            else{
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format(s):\n"
                                             + "`%stats`").reference(event.getMessage()).queue();
            
            }
        }
        else if((args[0].equalsIgnoreCase(ProbieBot.prefix + "kittyme"))){
            statTrack(ProbieBot.prefix+"kittyme");
            event.getChannel().sendTyping().queue();
            try{
                event.getChannel().sendMessage(kittyScrape("https://www.randomkittengenerator.com/")).reference(event.getMessage()).queue();
            }
            catch(Exception ex){
                event.getMessage().addReaction("❌").queue();
            }
        }
        else if((args[0].equalsIgnoreCase(ProbieBot.prefix + "musicrec"))){
            //statTrack(ProbieBot.prefix+"musicrec");
            if(args.length > 1){
                event.getChannel().sendTyping().queue();
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    sb.append(args[i]);
                    if(i != args.length-1){
                        sb.append(" ");
                    }
                }
                EmbedBuilder embed = musicURLScrape("https://www.newreleasesnow.com/genre?q="+sb.toString().replace(" ", "-"),sb.toString().replace(" ","-"));
                event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
            }
            else{
                event.getChannel().sendTyping().queue();
                event.getMessage().addReaction("❌").queue();
                event.getChannel().sendMessage("Please use the command in the following format: `%musicrec [genre]`").reference(event.getMessage()).queue();
                        
            }
            
        }
    }
    catch (Exception e){
        EmbedBuilder exception = new EmbedBuilder();
        exception.setTitle("Exception has been caught!");
        exception.addField("Exception (Test bot): " + e.getMessage(),"Please contact my owner and let him know I have failed him. A screenshot of what you did would also help.",false);
        event.getChannel().sendMessage(exception.build()).queue();
        exception.clear();
    }
        if(event.getChannel().getId().equals(countingChannel)){
            String message = event.getMessage().getContentRaw();
            TreeMap lc = new TreeMap();
            lc = LetterCount.count(message);
            if(message.contains("+") || message.contains("-") || message.contains("/") || message.contains("*") || message.contains("%") || message.contains("^")){
                int[] counts = new int[6];
                if(lc.containsKey('+')){
                    counts[0] = (Integer)lc.get('+');
                }
                else{
                    counts[0]=0;
                }
                if(lc.containsKey('-')){
                    counts[1] = (Integer)lc.get('-');
                }
                else{
                    counts[1]=0;
                }
                if(lc.containsKey('/')){
                    counts[2] = (Integer)lc.get('/');
                }
                else{
                    counts[2]=0;
                }
                if(lc.containsKey('*')){
                    counts[3] = (Integer)lc.get('*');
                }
                else{
                    counts[3]=0;
                }
                if(lc.containsKey('%')){
                    counts[4] = (Integer)lc.get('%');
                }
                else{
                    counts[4]=0;
                }
                if(lc.containsKey('^')){
                    counts[5] = (Integer)lc.get('^');
                }
                else{
                    counts[5]=0;
                }
                // Minimum of 2 operations
                int count=0;
                for (int i = 0; i < counts.length; i++) {
                    count+=counts[i];
                }
                if(count>=2){
                    // Calculate the number
                    double result = 0;
                    // Try postfix first
                    boolean canContinue=false;
                    try{
                        result = Calculator.evalPostFix(message);
                        canContinue = true;
                    }
                    catch(IllegalArgumentException e){
                        try{
                          result = Calculator.evalPostFix(Calculator.infixToPostfix(message));
                          canContinue = true;
                        }
                        catch(IllegalArgumentException ex){
                            canContinue = false;
                        }
                    }
                    int intResult = (int)result;
                    Scanner scan = null;
                    try{
                        scan = new Scanner(new File("count.count"));
                    }
                    catch(FileNotFoundException e){
                        try{
                            new File("count.count").createNewFile();
                            scan = new Scanner(new File("count.count"));
                        }
                        catch(IOException ex){
                        }
                    }
                    try{
                        if(scan!=null && canContinue){
                            int pastNumber = scan.nextInt();
                            String pastAuthor = null;
                            try{
                                pastAuthor = scan.next();
                            }
                            catch(NoSuchElementException exe){
                                if(pastNumber+1 != intResult){
                                    // Wrong answer
                                    PrintWriter out = null;
                                    try{
                                        out = new PrintWriter(new File("count.count"));
                                        out.write("0");
                                        out.close();
                                    }
                                    catch(IOException ex){}
                                    event.getMessage().addReaction("❌").queue();
                                    EmbedBuilder embed = new EmbedBuilder();
                                    embed.setTitle("Count has been reset to 0. Start at 1");
                                    embed.appendDescription(event.getAuthor().getAsMention() + " has messed up the count at **" + pastNumber +"**. Try again!");
                                    event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                                }
                                else{
                                    // Right answer
                                    PrintWriter out = null;
                                    try{
                                        out = new PrintWriter(new File("count.count"));
                                        out.write(""+intResult + " " + event.getAuthor().getId());
                                        out.close();
                                        event.getMessage().addReaction("✅").queue();
                                    }
                                    catch(IOException ex){}
                                }
                            }
                            if(pastAuthor!=null){
                                if(pastNumber+1 != intResult || pastAuthor.equals(event.getAuthor().getId())){
                                    // Wrong answer
                                    PrintWriter out = null;
                                    try{
                                        out = new PrintWriter(new File("count.count"));
                                        out.write("0");
                                        out.close();
                                    }
                                    catch(IOException ex){}
                                    event.getMessage().addReaction("❌").queue();
                                    EmbedBuilder embed = new EmbedBuilder();
                                    embed.setTitle("Count has been reset to 0. Start at 1");
                                    embed.appendDescription(event.getAuthor().getAsMention() + " has messed up the count at **" + pastNumber +"**. Try again!");
                                    event.getChannel().sendMessage(embed.build()).reference(event.getMessage()).queue();
                                }
                                else{
                                    // Right answer
                                    PrintWriter out = null;
                                    try{
                                        out = new PrintWriter(new File("count.count"));
                                        out.write(""+intResult + " " + event.getAuthor().getId());
                                        out.close();
                                        event.getMessage().addReaction("✅").queue();
                                    }
                                    catch(IOException ex){}
                                }
                            }
                        }
                    }
                    catch(NoSuchElementException e){
                    }
                }
                else{}
            }
            else{}
        }
    }
    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if(event.getAuthor().getId().equals("540957458944884746")){
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if(args[0].equalsIgnoreCase(ProbieBot.prefix + "message")){
                // Given a channel, it will send a message
                String channelid = null;
                try{
                    if(event.getMessage().getReferencedMessage()!=null){
                        String[] argsref = event.getMessage().getReferencedMessage().getContentRaw().split("\\s+");
                        channelid = argsref[0];
                    }
                    else{
                        channelid = args[1];
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    event.getMessage().addReaction("❌").queue();
                }
                if (channelid != null){
                    TextChannel channel = null;
                    try{
                        channel = event.getJDA().getTextChannelById(channelid);
                    }
                    catch (Exception ex){
                        event.getMessage().addReaction("❌").queue();
                    }
                    if(channel != null){
                        StringBuilder message = new StringBuilder();
                        if(event.getMessage().getReferencedMessage()!=null){
                            for (int i = 1; i < args.length; i++) {
                                message.append(args[i]);
                                if(i!=args.length-1){
                                    message.append(" ");
                                }
                            }
                            if(message.toString().length()!=0){
                                channel.sendMessage(message.toString()).queue();
                            }
                        }
                        else{
                            for (int i = 2; i < args.length; i++) {
                                message.append(args[i]);
                                if(i!=args.length-1){
                                    message.append(" ");
                                }
                            }
                            if(message.toString().length()!=0){
                                channel.sendMessage(message.toString()).queue();
                            }
                        }
                    }
                }
            }
            else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "reply")){
                // Given a channelid and messageid, it will reply
                String channelid = null;
                String messageid = null;
                try{
                    if(event.getMessage().getReferencedMessage()!=null){
                        String[] argsref = event.getMessage().getReferencedMessage().getContentRaw().split("\\s+");
                        channelid = argsref[0];
                        messageid = argsref[1];
                    }
                    else{
                        channelid = args[1];
                        messageid = args[2];
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    event.getMessage().addReaction("❌").queue();
                }
                if (channelid != null){
                    TextChannel channel = null;
                    try{
                        channel = event.getJDA().getTextChannelById(channelid);
                    }
                    catch (Exception ex){
                        event.getMessage().addReaction("❌").queue();
                    }
                    if(channel != null){
                        StringBuilder message = new StringBuilder();
                        if(event.getMessage().getReferencedMessage()!=null){
                            for (int i = 1; i < args.length; i++) {
                                message.append(args[i]);
                                if(i!=args.length-1){
                                    message.append(" ");
                                }
                            }
                            if(message.toString().length()!=0){
                                channel.sendMessage(message.toString()).referenceById(messageid).queue();
                            }
                        }
                        else{
                            for (int i = 3; i < args.length; i++) {
                                message.append(args[i]);
                                if(i!=args.length-1){
                                    message.append(" ");
                                }
                            }
                            if(message.toString().length()!=0){
                                channel.sendMessage(message.toString()).referenceById(messageid).queue();
                            }
                        }
                    }
                }
            }
            else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "react")){
                // Given a channel id and messageid, it will react
                String channelid = null;
                String messageid = null;
                try{
                    if(event.getMessage().getReferencedMessage()!=null){
                        String[] argsref = event.getMessage().getReferencedMessage().getContentRaw().split("\\s+");
                        channelid = argsref[0];
                        messageid = argsref[1];
                    }
                    else{
                        channelid = args[1];
                        messageid = args[2];
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    event.getMessage().addReaction("❌").queue();
                }
                if (channelid != null){
                    TextChannel channel = null;
                    try{
                        channel = event.getJDA().getTextChannelById(channelid);
                    }
                    catch (Exception ex){
                        event.getMessage().addReaction("❌").queue();
                    }
                    if(channel != null){
                        Emote emoji = null;
                        try{
                            emoji = event.getMessage().getEmotes().get(0);
                        }
                        catch(Exception ex){
                            event.getMessage().addReaction("❌").queue();
                        }
                        if(emoji!=null){
                            channel.addReactionById(messageid, emoji).queue();
                        }
                    }
                }
            }
            else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "update")){
                // Time for subprocesses!!! :HYPERS:
                try{ 
                    ProcessBuilder git = new ProcessBuilder().command("git", "clone","https://github.com/probstcj/ProbieBotJar").inheritIO().directory(new File("/home/probiebot/Desktop/ProbieBot"));
                    Process procGit = git.start();
                    procGit.waitFor();
                    procGit.destroy();
                    System.out.println("Clear 1");
                    ProcessBuilder move = new ProcessBuilder().command("mv", "/home/probiebot/Desktop/ProbieBot/ProbieBotJar/ProbieBot.jar","/home/probiebot/Desktop/ProbieBot").inheritIO().directory(new File("/home/probiebot/Desktop/ProbieBot"));
                    Process procMove = move.start();
                    procMove.waitFor();
                    procMove.destroy();
                    System.out.println("Clear 2");
                    ProcessBuilder rem = new ProcessBuilder().command("rm", "-r","-f","ProbieBotJar").inheritIO().directory(new File("/home/probiebot/Desktop/ProbieBot"));
                    Process procRem = rem.start();
                    procRem.waitFor();
                    procRem.destroy();
                    System.out.println("Clear 3");
                    ProcessBuilder end = new ProcessBuilder().command("java","-jar","ProbieBot.jar").inheritIO().directory(new File("/home/probiebot/Desktop/ProbieBot"));
                    Process procEnd = end.start();
                    System.out.println("Clear 4");
                    System.exit(0);
                }
                catch(Exception ex){
                    event.getChannel().sendMessage("Error: "+ex.toString()).queue();
                }
            }
            else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "accept")){
                if(args.length < 2){
                    event.getMessage().addReaction("❌").queue();
                }
                else if(event.getMessage().getReferencedMessage() == null){
                    event.getMessage().addReaction("❌").queue();
                }
                else{
                    EmbedBuilder embed = new EmbedBuilder();
                    String refMess = event.getMessage().getReferencedMessage().getContentRaw();
                    String[] argsRef = refMess.split("\\s+");
                    String temp = argsRef[0];
                    StringBuilder namePlace = new StringBuilder();
                    int i = 0;
                    while(!temp.equals("Account")){
                        namePlace.append(temp);
                        temp = argsRef[++i];
                    }
                    i = 0;
                    String channelID = null, userID = null, messageID = null;
                    temp = args[0];
                    while(!temp.equals("ID:")){
                        i++;
                        temp = argsRef[i];
                    }
                    i++;
                    channelID = argsRef[i];
                    i+= 3;
                    userID = argsRef[i];
                    i+= 3;
                    messageID = argsRef[i];
                    event.getJDA().getGuildById(boisGuild).getTextChannelById(channelID).sendMessage("DM sent").reference(event.getJDA().getGuildById(boisGuild).getTextChannelById(channelID).retrieveMessageById(messageID).complete()).queue();
                    String title = namePlace + " Account Generated";
                    StringBuilder sb = new StringBuilder();
                    switch(namePlace.toString()){
                        case "APizza": 
                            sb.append("Anthony's Pizza:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your FREE pizza!:__\n" +
                                "1) Navigate to the [Anthonys Coal Fired Pizza](https://acfp.com/home) site\n" +
                                "2) Login with the given credentials\n" +
                                "3) After 24 hours the account will have a reward for a free 12 inch pizza\n" +
                                "4) Enjoy!");
                            break;
                        case "Arbys":
                            sb.append("Arby's:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your FREE CLASSIC ROAST BEEF WITH PURCHASE!:__\n" +
                                "1) Navigate to the Arby's website or app\n" +
                                "2) Login with the given credentials\n" +
                                "3) Head to deals and select a location, then the reward should show up!\n" +
                                "4) Enjoy only your Birthday!");
                            break;
                        case "Bagel": 
                            sb.append("Einstein Bagel:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free FREE Bagel:__\n" +
                                "1) Navigate to the Einstein bagels app or website\n" +
                                "2) Sign in with the credentials and navigate to the rewards\n" +
                                "3) Enjoy your bogo for a free bagel!");
                            break;
                        case "BJ's": 
                            sb.append("BJ's:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free dessert:__\n" +
                                "1) Navigate [here](https://www.bjsrestaurants.com/account/login)\n" +
                                "2) Sign in with the credentials\n" +
                                "3) Head to available rewards and enjoy only only on your birthday!.");
                            break;
                        case "Bojangle": 
                            sb.append("Bojangles:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free sandwich:__\n" +
                                "1) Login with the credentials on the bojangles app or website\n" +
                                "2) Go to offers and enjoy!\n");
                            break;
                        case "Chilis": 
                            sb.append("Chilis:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your Drink, and one of the following: 1/2 order of Texas Cheese Fries, 2 eggrolls, Guacamole, OR Skillet Queso:__\n" +
                                "1) Navigate to the Chilis App or website and sign in with the credentials given\n" +
                                "2) Go to the my account tab and change the name on the account to your name\n" +
                                "3) Head to the rewards section and enjoy your free food only only on your birthday!");
                            break;
                        case "Cinnabon": 
                            sb.append("Cinnabon:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your choice of a FREE 16oz cold brew:__\n" +
                                "1) Navigate to the cinnabun app or website\n" +
                                "2) After 5-20 minutes the account should have a reward for a free 16oz cold brew\n" +
                                "3) Enjoy only on your birthday!");
                            break;
                        case "California Pizza Kitchen": 
                            sb.append("California Pizza Kitchen:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free small plate and free dessert:__\n" +
                                "1) Navigate [here](https://www.cpk.com/sign-in)\n" +
                                "2) Sign in with the credentials\n" +
                                "3) Go to rewards and enjoy your free plate and free dessert only on your birthday!");
                            break;
                        case "Denny's": 
                            sb.append("Denny's:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your FREE triple double slam!:__\n" +
                                "1) Navigate to the Denny's website or app\n" +
                                "2) Login with the given credentials\n" +
                                "3) Within the hour the account should have a reward for a free triple double slam!\n" +
                                "4) Enjoy only on your birthday!");
                            break;
                        case "Farmersdog": 
                            sb.append("Farmersdog:\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("Navigate [here]("+args[1]+") with the referral code to redeem your dog food.");
                            break;
                        case "Firehouse": 
                            sb.append("Firehouse:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free FREE Firehouse sub:__\n" +
                                "1) Navigate to the Firehouse App or website\n" +
                                "2) Log in with the given credentials, wait until tomorrow.\n" +
                                "3) Enjoy your free firehouse sub that is located in the rewards section only on your birthday");
                            break;
                        case "Goodchop": 
                            sb.append("Goodchop:\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your $50 off good chop order:__\n");
                            sb.append("Navigate [here](https://www.goodchop.com/factor) and use code +"+args[1]+" for $50 off your order");
                            break;
                        case "Hello Fresh": 
                            sb.append("Hello Fresh:\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("Navigate [here]("+args[1]+") with the referral code to redeem your dog food.");
                            break;
                        case "Krispy Kreme": 
                            sb.append("Krispy Kreme:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free 13 donuts:__\n" +
                                "1) Navigate tp the app\n" +
                                "2) Sign in with the credentials\n" +
                                "3) Head to offers and you will be able to enjoy a free donut immediately\n" +
                                "4) After waiting 12-24 hours later the account will then have a new offer in the offers tab that is for 12 free donuts only on your birthday!\n" +
                                "5) Enjoy!");
                            break;
                        case "Revive": 
                            sb.append("Revive:\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("Navigate [here](https://revivesuperfoods.com/) and use promo code `WELCOME50` or `RSGSB50` for 50% off your order");
                            break;
                        case "Smoothie King": 
                            sb.append("Smoothie King:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your FREE smoothie!:__\n" +
                                "1) Navigate to the Smoothie King's app\n" +
                                "2) Login with the given credentials\n" +
                                "3) Go into rewards to redeem your free rewards\n" +
                                "4) Enjoy!\n" +
                                "If the reward is not showing up, try generating the account on a Friday");
                            break;
                        case "Subway": 
                            sb.append("Subway:\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("Go to the Subway app, and use promo code `FREESUB` for BOGO footlong subs.");
                            break;
                        case "Taco Bell": 
                            sb.append("Taco Bell:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free FREE Tacobell taco:__\n" +
                                "1) Navigate to the Tacobell app or website and login with the given credentials\n" +
                                "2) Wait 5-10 minutes for the rewards to show up, they usually aren't in the account right away\n" +
                                "3) Enjoy your free food!");
                            break;
                        case "Treats": 
                            sb.append("Treats:\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free cat or dog treats:__\n\n" +
                                "1) On mobile, use [this link]("+args[1]+") to download the app `Buddies`\n" +
                                "2) Continue to the app and create an account\n" +
                                "3) Once created, the account will have 700 points on it and you are able to redeem free dog or cat treats\n" +
                                "4) Shipping is free!\n" +
                                "5) Enjoy!");
                            break;
                        case "Tropical": 
                            sb.append("Tropical:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your FREE smoothie!:__\n" +
                                "1) Navigate to the Tropical app\n" +
                                "2) Login with the given credentials\n" +
                                "3) Head to a store near you and make a purchase above $5 and make sure to scan the app when purchasing\n" +
                                "4) After 24 hours the account will have a free smoothie in the rewards!");
                            break;
                        case "Back Yard Burger": 
                            sb.append("Back Yard Burger:\n\n");
                            sb.append("Please wait for email to be sent, it will contain a coupon for a free burger w/ purchase");
                            break;
                        case "Baskin Robins":
                            sb.append("Baskin Robins:\n\n");
                            sb.append("Please wait up to 6 hours to receive email with BOGO coupon.");
                            break;
                        case "Benihana":
                            sb.append("Benihana:\n");
                            sb.append("Please wait up to 24 hours to receive email for $30 certificate");
                            break;
                        case "Buffalo Wild Wings":
                            sb.append("Buffalo Wild Wings:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("At the first of the next month you will be emailed a coupon for 6 free wings, check the account and your email at this time to redeem only on your birthday only!\n");
                            break;
                        case "Captain D's":
                            sb.append("Captain D's:\n");
                            sb.append("Please wait up to 6 hours to receive email for free 1 Piece Fish and Fries coupon");
                            break;
                        case "Del Taco":
                            sb.append("Del Taco:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free FREE DelTacos:__\n" +
                                "1) Navigate to the Del Taco's App and sign in with the credentials given, use the verify email email sent to the email used to verify the account if necessary\n" +
                                "2) Head to the rewards section and enjoy your free tacos only on your birthday!");
                            break;
                        case "Dippin Dots":
                            sb.append("Dippin Dots:\n");
                            sb.append("Please wait up to 24 hours to receive email for rewards.");
                            break;
                        case "Power Burn Energy Drink":
                            sb.append("Power Burn Energy Drink:\n");
                            sb.append("Please check your email for the free energy drink coupon.");
                            break;
                        case "iHop":
                            sb.append("iHop:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free stack of pancakes:__\n" +
                                "1) Wait 24-48 hours to receive your free stack in your emails\n" +
                                "2) Use the code in the email to redeem your free pancakes only on your birthday!\n");
                            break;
                        case "McDonalds":
                            sb.append("McDonalds:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free FREE McDonald's big mac:__\n" +
                                "1) Download the McDonald's App\n" +
                                "2) Go to your emails and click on the verify email email that was sent to you\n" +
                                "3) Login to the mcdonalds app and enable your location\n" +
                                "3) Once your location is enabled, check your deals and you will have a free big mac available!\n\n" +
                                "If you get errors logging in, just get a new verification code or try the verification code again.");
                            break;
                        case "Popeyes":
                            sb.append("Popeyes:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your choice of a FREE regular side, small drink, or Apple Pie:__\n" +
                                "1) Navigate to the Popeyes's App or website and sign in with the email given, use the verify email email sent to the email used to verify the account using the auth code\n" +
                                "2) Head to the rewards section and enjoy your free food only on your birthday!\n" +
                                "3) if you had to use a generated email, check the inbox when you login for the verification code.");
                            break;
                        case "Steak":
                            sb.append("Black Angus Steakhouse:\n\n");
                            sb.append("Please wait up to 48 hours to receive email regarding your free steak dinner.\n");
                            break;
                        case "Steak n' Shake":
                            sb.append("Steak n' Shake:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("Go to the Steak n' Shake app and login with the credentials to redeem your shake only on your birthday");
                            break;
                        case "Texas Roadhouse":
                            sb.append("Texas Roadhouse:\n\n");
                            sb.append("Wait about 24 hours to receive your coupon for a free entree with purchase\n");
                            break;
                        case "Waffle House":
                            embed.setTitle("Waffle House:\n\n");
                            sb.append("Wait about 24 hours to receive your coupon for a free entree with purchase");
                            break;
                        case "Wetzel":
                            sb.append("Wetzel:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your FREE Pretzel!:__\n" +
                                "1) Navigate to the Wetzel Prezel app and sign in with the credentials given\n" +
                                "2) Use the verify email sent to the email provided to complete sign in\n" +
                                "3) Head to the rewards section and enjoy your free pretzel only on your birthday!");
                            break;
                        case "Whataburger":
                            sb.append("Whataburger:\n\n");
                            sb.append("**Account Details**:\n");
                            sb.append("Username: `"+args[1]+"`\n");
                            sb.append("Password: `"+args[2]+"`\n\n");
                            sb.append("**Instructions:**\n");
                            sb.append("__How to redeem your free whataburger:__\n" +
                                "1) Download the whataburger app\n" +
                                "2) Login with the given credentials, you may need to accept a verification code to the email used to sign up\n" +
                                "3) Within the hour there should be a reward for a free whataburger on the account!\n" +
                                "4) Enjoy!");
                            break;
                        default: break;
                    }
                    embed.setTitle(title);
                    embed.setDescription(sb);
                    event.getJDA().getGuildById(boisGuild).retrieveMemberById(userID).complete().getUser().openPrivateChannel().complete().sendMessage(embed.build()).queue();
                    if(args.length > 1){
                        event.getJDA().getGuildById(boisGuild).retrieveMemberById(userID).complete().getUser().openPrivateChannel().complete().sendMessage(args[1]).queue();
                    }
                    if(args.length == 3){
                        event.getJDA().getGuildById(boisGuild).retrieveMemberById(userID).complete().getUser().openPrivateChannel().complete().sendMessage(args[2]).queue();
                    }
                }
            }
            else if(args[0].equalsIgnoreCase(ProbieBot.prefix + "deny")){
                if(args.length > 2){
                    event.getMessage().addReaction("❌").queue();
                }
                else{
                    EmbedBuilder embed = new EmbedBuilder();
                    String refMess = event.getMessage().getReferencedMessage().getContentRaw();
                    String[] argsRef = refMess.split("\\s+");
                    String temp = argsRef[0];
                    StringBuilder namePlace = new StringBuilder();
                    int i = 0;
                    while(!temp.equals("Account")){
                        namePlace.append(temp);
                        temp = argsRef[++i];
                    }
                    i = 0;
                    String channelID = null, userID = null, messageID = null;
                    temp = args[0];
                    while(!temp.equals("ID:")){
                        i++;
                        temp = argsRef[i];
                    }
                    i++;
                    channelID = argsRef[i];
                    i+= 3;
                    userID = argsRef[i];
                    i+= 3;
                    messageID = argsRef[i];
                    event.getJDA().getGuildById(boisGuild).getTextChannelById(channelID).sendMessage("Request Denied, try again later").reference(event.getJDA().getGuildById(boisGuild).getTextChannelById(channelID).retrieveMessageById(messageID).complete()).queue();
                    
                }
            }
        }
    }
    public String embedToString(List<MessageEmbed> list){
        List<Field> fields = null;
        ArrayList<String> titleDesc = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            fields = list.get(i).getFields();
            if(list.get(i).getDescription()!=null){
                titleDesc.add(list.get(i).getDescription());
            }
            if(list.get(i).getTitle()!=null){
                titleDesc.add(list.get(i).getTitle());
            }
        }
        for (int i = 0; i < titleDesc.size(); i++) {
            sb.append(titleDesc.get(i));
            sb.append(" ");
        }
        if(fields == null){}
        else{
            for (int i = 0; i < fields.size(); i++) {
                 sb.append(fields.get(i).getName());
                 sb.append(" ");
                 sb.append(fields.get(i).getValue());
                 sb.append(" ");
            }
        }
        return sb.toString();
    }
    private String extractTitle(String content) {
        final Document doc = Jsoup.parse(content);
        final Element titleElement = doc.select("title").first();
        return titleElement.text();
    }
    private EmbedBuilder weatherScrape(String URL) {
      int temperature = 0;
      String cloudyness = null;
      int dewPoint = 0;
      int humidity = 0;
      String citystate = null;
      int highTemp = 0;
      int lowTemp = 0;
      String highTempString = null;
      String lowTempString = null;
      final String httpsUrl = URL;
      try {
        final URL url = new URL(httpsUrl);
         URLConnection con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         PrintWriter toFile = new PrintWriter(new File("test.test"));
         final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
         String input;
         StringBuilder sb = new StringBuilder();
         while ((input = br.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br.close();
         
         
         Scanner fromFile = new Scanner(new File("test.test"));
         
         while(fromFile.hasNext()){
             String data = null;
             data = fromFile.next();
             // Scan for temperature
            if(data.equals("Weather")){
                if(fromFile.next().equals("Conditions</span><span")){
                    for (int i = 0; i < 28; i++) {
                        fromFile.next();
                    }
                    String temp = fromFile.next();
                    temperature = Integer.parseInt(temp.substring(temp.indexOf("\">")+2, temp.indexOf("</")));
                }
            }
            // Scan for high temperature (left)
            if(data.contains("condition-data")){
                fromFile.next();
                if(fromFile.next().contains("hi-lo")){
                    fromFile.next();
                    String temp = fromFile.next();
                    if(temp.contains("hi")){
                        temp = temp.substring(temp.indexOf("\"hi\">")+5,temp.indexOf("°<"));
                        try{
                            highTemp = Integer.parseInt(temp);
                        }
                        catch(NumberFormatException e){
                            highTempString = temp;
                        }
                        for (int i = 0; i < 5; i++) {
                            fromFile.next();
                        }
                        temp = fromFile.next();
                        temp = temp.substring(temp.indexOf("\"lo\">")+5, temp.indexOf("°<"));
                        try{
                            lowTemp = Integer.parseInt(temp);
                        }
                        catch(NumberFormatException e){
                            lowTempString = temp;
                        }
                    }
                }
            }
            // Scan for low temperature (right)
            // Get new City, ST
            if(data.contains("<title>")){
                StringBuilder sbd = new StringBuilder();
                sbd.append(data);
                sbd.append(" ");
                String temp = data;
                while(!(temp = fromFile.next()).equals("Weather")){
                    sbd.append(temp);
                    sbd.append(" ");
                }
                for (int i = 0; i < 7; i++) {
                    sbd.deleteCharAt(0);
                }
                citystate = sbd.toString();
            }
             
            // Scan for cloudyness
             else if(data.contains("Clouds")){
                 fromFile.next();
                 if(fromFile.next().equals("class=\"small-8")){
                     for (int i = 0; i < 2; i++) {
                         fromFile.next();
                     }
                     StringBuilder sbd = new StringBuilder();
                     String temp = fromFile.next();
                     if(temp.contains("</span")){
                         sbd.append(temp.substring(temp.indexOf(">")+1,temp.indexOf("</span")));
                     }
                     else{
                        sbd.append(temp.substring(temp.indexOf(">")+1));
                     }
                     sbd.append(" ");
                     String temp2 = fromFile.next();
                     if(temp2.contains("</span")){
                         sbd.append(temp2.substring(0,temp2.indexOf("</span")));
                        sbd.append(" ");
                     }
                     else if(!temp2.contains("_ngcontent")){
                         sbd.append(temp2);
                        sbd.append(" ");
                     }
                     String temp3 = fromFile.next();
                     if(temp3.contains("</span")){
                         sbd.append(temp3.substring(0,temp3.indexOf("</span")));
                        sbd.append(" ");
                     }
                     cloudyness=sbd.toString().substring(0,sbd.length()-1);
                 }
                 
             }
             // Scan for Dew Point
             else if(data.contains("columns\">Dew")){
                 if(fromFile.next().equals("Point</div><div")){
                     for (int i = 0; i < 14; i++) {
                         fromFile.next();
                     }
                     String temp = fromFile.next();
                     temp = temp.substring(temp.indexOf(">")+1,temp.indexOf("</span"));
                     dewPoint = Integer.parseInt(temp);
                 }
             }
             // Scan for Relative Humidity
             else if(data.contains("Humidity")){
                 fromFile.next();
                 if(fromFile.next().equals("class=\"small-8")){
                     for (int i = 0; i < 12; i++) {
                         fromFile.next();
                     }
                     String temp = fromFile.next();
                     temp = temp.substring(temp.indexOf(">")+1,temp.indexOf("</span"));
                     humidity = Integer.parseInt(temp);
                 }
             }
             
         }
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      EmbedBuilder eb = new EmbedBuilder();
      eb.setTitle("In " +citystate+":");
      eb.setDescription("Temperature: "+temperature+"°F\n"
              +         "High/Low: " +(highTempString==null?highTemp:highTempString) + "°F/" + (lowTempString==null?lowTemp:lowTempString) + "°F\n"
              +         "Dew Point: "+dewPoint+"°F\n"
              +         "Relative Humidity: "+humidity+"%\n"
              +         "Cloudiness: "+cloudyness);
      eb.setColor(Color.GREEN);
      //(new File("test.test")).delete();
      return eb;
   }
    private EmbedBuilder gasScrape(String URL){
        String citystate = null;
        ArrayList<GasPrice> tempStations = new ArrayList<>();
          ArrayList<GasPrice> stations = new ArrayList<>();
          ArrayList<String> names = new ArrayList<>();
          ArrayList<String> addresses = new ArrayList<>();
          ArrayList<Double> prices = new ArrayList<>();
        try{
        final String httpsUrl = URL;
        final URL url = new URL(httpsUrl);
         URLConnection con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         PrintWriter toFile = new PrintWriter(new File("gas.gas"));
         final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
         String input;
         StringBuilder sb = new StringBuilder();
         while ((input = br.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br.close();
         Scanner fromFile = new Scanner(new File("gas.gas"));
         
          while(fromFile.hasNext()){
             String data = null;
             data = fromFile.next();
             // Get city and state
             if(data.contains("SearchResultsPage-module__summary")){
                 for (int i = 0; i < 13; i++) {
                     fromFile.next();
                 }
                 if(fromFile.next().contains("SearchResultsPage-module__resultsHeader")){
                     for (int i = 0; i < 3; i++) {
                         fromFile.next();
                     }
                     StringBuilder sbd = new StringBuilder();
                     sbd.append(fromFile.next());
                    sbd.append(" ");
                     String temp = null;
                     while(!(temp = fromFile.next()).contains("</h3>")){
                        sbd.append(temp);
                        sbd.append(" ");
                    }
                    sbd.append(temp.substring(0,temp.indexOf("</h3>")));
                    citystate = sbd.toString();
                 }
             }
             // Search for gas prices
             else if(data.contains("StationDisplay-module__stationNameHeader")){
                 fromFile.next();
                 StringBuilder sbd = new StringBuilder();
                 String temp = fromFile.next();
                 sbd.append(temp.substring(temp.indexOf("\">")+2));
                 sbd.append(" ");
                 if(!sbd.toString().contains("</a>")){
                    while(!(temp = fromFile.next()).contains("</a>")){
                        sbd.append(temp);
                        sbd.append(" ");
                    }
                 }
                 sbd.append(temp);
                 names.add(sbd.toString().substring(0,sbd.toString().indexOf("</a>")).replace("&#x27;", "'").replace("&amp;", "&"));
                 while(!(temp = fromFile.next()).contains("module__address___2")){}
                 sbd = new StringBuilder();
                 sbd.append(temp);
                 sbd.append(" ");
                 while(!(temp = fromFile.next()).contains("</div>")){
                     sbd.append(temp);
                     sbd.append(" ");
                 }
                 sbd.append(temp);
                 temp = sbd.toString();
                 temp = temp.substring(temp.indexOf("\">")+2,temp.indexOf("</div>"));
                 temp = temp.replace("<br/>", ", ");
                 temp = temp.replace("&#x27;", "'").replace("&amp;", "&");
                 addresses.add(temp);
                 while(!(temp = fromFile.next()).contains("StationDisplayPrice-module__price")){}
                 while(!(temp = fromFile.next()).contains("StationDisplayPrice-module__price")){}
                 if(temp.contains("</span>")){
                    temp = temp.substring(temp.indexOf("\">$")+3,temp.indexOf("</span>"));
                 }
                 else{
                     temp = "null";
                 }
                try{
                     prices.add(Double.parseDouble(temp));
                 }
                 catch(NumberFormatException e){
                     prices.add(0.0);
                 }
             }
          }
          for (int i = 0; i < prices.size(); i++) {
        try{
           tempStations.add(new GasPrice(names.get(i), addresses.get(i), prices.get(i)));
        }
        catch(IllegalArgumentException e){}
        }
        double[] tempArray = new double[tempStations.size()];
        for (int i = 0; i <tempStations.size(); i++) {
            tempArray[i] = tempStations.get(i).getPrice();
        }
        Arrays.sort(tempArray);
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = 0; j < tempStations.size(); j++) {
                if(tempArray[i] == tempStations.get(j).getPrice()){
                    stations.add(tempStations.get(j));
                    tempStations.remove(j);
                }
            }
        }
        }
        catch (Exception e){
        }
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Gas Prices in " + citystate + ":");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stations.size(); i++) {
            String temp = ""+stations.get(i).getPrice();
            if(temp.length()==3){
                sb.append((i+1)+") "+stations.get(i).getName()+", "+stations.get(i).getAddress()+": $"+stations.get(i).getPrice()+"0\n");
            }
            else{
                sb.append((i+1)+") "+stations.get(i).getName()+", "+stations.get(i).getAddress()+": $"+stations.get(i).getPrice()+"\n");
            }
        }
        embed.setDescription(sb.toString());
        embed.setColor(Color.GREEN);
        //new File("gas.gas").delete();
            
        return embed;
    }
    private String puppyScrape(String URL){
        try{
        final String httpsUrl = URL;
        final URL url = new URL(httpsUrl);
         URLConnection con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         PrintWriter toFile = new PrintWriter(new File("puppy.puppy"));
         final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
         String input;
         StringBuilder sb = new StringBuilder();
         while ((input = br.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br.close();
         
         
         Scanner fromFile = new Scanner(new File("puppy.puppy"));
         String temp = null;
          while(fromFile.hasNext()){
              if(fromFile.next().contains("thumbnail-col-1")){
                  for (int i = 0; i < 5; i++) {
                      fromFile.next();
                  }
                  temp = fromFile.next();
                  temp = temp.substring(temp.indexOf("\"")+1,temp.lastIndexOf("\""));
                  temp = "https://www.generatormix.com/" + temp;
              }
          }
         //(new File("puppy.puppy")).delete();
          return temp;
        }
        catch(Exception ex){
            return null;
        }
        
    }
    private String kittyScrape(String URL){
        try{
        final String httpsUrl = URL;
        final URL url = new URL(httpsUrl);
         URLConnection con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         PrintWriter toFile = new PrintWriter(new File("kitty.kitty"));
         final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
         String input;
         StringBuilder sb = new StringBuilder();
         while ((input = br.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br.close();
         
         
         Scanner fromFile = new Scanner(new File("kitty.kitty"));
         String temp = null;
          while(fromFile.hasNext()){
              boolean link = false;
              if(fromFile.next().contains("post_content") && !link){
                  boolean linkFound = false;
                  while(!linkFound){
                      temp = fromFile.next();
                      if(temp.contains("src=")){
                          link = true;
                          linkFound = true;
                          temp = temp.substring(temp.indexOf("src=\"")+5,temp.lastIndexOf("\""));
                      }
                  }
              }
          }
          (new File("kitty.kitty")).delete();
          return temp;
        }
        catch(Exception ex){
            return null;
        }
        
    }
    private EmbedBuilder anagramScrape(String URL, String userIn){
        try{
        final String httpsUrl = URL;
        final URL url = new URL(httpsUrl);
         URLConnection con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         PrintWriter toFile = new PrintWriter(new File("anagram.anagram"));
         final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
         String input;
         StringBuilder sb = new StringBuilder();
         while ((input = br.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br.close();
         
         
         Scanner fromFile = new Scanner(new File("anagram.anagram"));
         String[] anagrams = new String[10];
          while(fromFile.hasNext()){
              if(fromFile.next().contains("document.body.style.cursor='wait';")){
                  for (int i = 0; i < 3; i++) {
                      fromFile.next();
                  }
                  String data = null;
                  StringBuilder sbd = new StringBuilder();
                  int index = 0;
                  while(!(data = fromFile.next()).contains("<script>") && anagrams[9]==null){
                      if(data.contains("</b>")){
                          String temp;
                          temp = data.substring(data.indexOf("</b><br>")+8);
                          if(temp.contains("<br>")){
                              sbd.append(temp.substring(0,temp.indexOf("<br>")));
                              anagrams[index++] = sbd.toString();
                              sbd = new StringBuilder();
                          }
                          else{
                            sbd.append(temp);
                            sbd.append(" ");
                          }
                      }
                      else{
                          if(data.contains("<br>")){
                              sbd.append(data.substring(0,data.indexOf("<br>")));
                              anagrams[index++] = sbd.toString();
                              sbd = new StringBuilder();
                          }
                          else{
                            sbd.append(data);
                            sbd.append(" ");
                          }
                      }
                  }
              }
          }
          EmbedBuilder embed = new EmbedBuilder();
          embed.setTitle("Anagrams for " + userIn);
            for (int i = 0; i < 10; i++) {
                if(anagrams[i]!=null){
                    embed.appendDescription(i+1+". "+anagrams[i]+"\n");
                }
            }
            embed.setColor(Color.GREEN);
          (new File("anagram.anagram")).delete();
          return embed;
        }
        catch(Exception ex){
            return null;
        }
        
    }
    private String flmanScrape(String URL){
        try{
        final String httpsUrl = URL;
        final URL url = new URL(httpsUrl);
         URLConnection con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         PrintWriter toFile = new PrintWriter(new File("flman.flman"));
         final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         String input;
         StringBuilder sb = new StringBuilder();
         while ((input = br.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br.close();
         Scanner fromFile = new Scanner(new File("flman.flman"));
         String temp = null;
         String link = null;
          while(fromFile.hasNext() && link==null){
              temp = fromFile.next();
              if(temp.contains("url=http")){
                  link = temp.substring(temp.indexOf("url=")+4,temp.lastIndexOf("&amp;ved"));
              }
          }
        (new File("flman.flman")).delete();
          return link;
        }
        catch(Exception ex){
            return null;
        }
    }
    private String radarScrape(String URL){
        try{
        final String httpsUrl = URL;
        final URL url = new URL(httpsUrl);
         URLConnection con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         PrintWriter toFile = new PrintWriter(new File("radar.radar"));
         final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         String input;
         StringBuilder sb = new StringBuilder();
         while ((input = br.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br.close();
         Scanner fromFile = new Scanner(new File("radar.radar"));
         String temp = null;
         String link = null;
          while(fromFile.hasNext() && link==null){
              temp = fromFile.next();
              if(temp.contains("Radar")){
                  fromFile.next();
                  temp = fromFile.next();
                  if(temp.contains("Satellite")){
                      temp = fromFile.next();
                      if(temp.contains("</h4>")){
                          fromFile.next();
                          temp = fromFile.next();
                          link = temp.substring(temp.indexOf("station/")+8,temp.indexOf("/standard"));
                      }
                  }
              }
          }
          
        (new File("radar.radar")).delete();
        link = "https://radar.weather.gov/ridge/standard/"+link.toUpperCase()+"_loop.gif";
          return link;
        }
        catch(Exception ex){
            return null;
        }
    }
    private String latLongScrape(String zip){
        String latlon = null;
        Scanner scan = null;
        try{
            scan = new Scanner(new File("zipcodes.code"));
        }
        catch (FileNotFoundException ex){}
        while(scan.hasNext() && latlon == null){
            String temp = scan.nextLine();
            if(temp.contains(zip+",")){
                latlon = temp;
            }
        }
        latlon = latlon.substring(latlon.indexOf(",")+1);
        
        return latlon;
    }
    private EmbedBuilder musicURLScrape(String URL, String genre){
        try{
        final String httpsUrl = URL;
        final URL url = new URL(httpsUrl);
         URLConnection con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         PrintWriter toFile = new PrintWriter(new File("mus.mus"));
         final BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

         String input;
         StringBuilder sb = new StringBuilder();
         while ((input = br.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br.close();
         
         
         Scanner fromFile = new Scanner(new File("mus.mus"));
         String temp = null;
         int page = 1;
          while(fromFile.hasNext()){
              temp = fromFile.next();
              if(temp.contains(genre)){
                  try{
                      page = Integer.parseInt(temp.substring(temp.indexOf("\">")+2,temp.lastIndexOf("</a>")));
                  }
                  catch(NumberFormatException ex){}
                  catch(StringIndexOutOfBoundsException ex){}
              }
          }
          Random rand = new Random();
          page = rand.nextInt(page)+1;
            URL = "https://www.newreleasesnow.com/genre/p"+page+"?q="+genre;
          //(new File("mus.mus")).delete();
          final String httpsUrl2 = URL;
        final URL url2 = new URL(httpsUrl2);
        con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         toFile = new PrintWriter(new File("mus.mus"));
         final BufferedReader br2 = new BufferedReader(new InputStreamReader(con.getInputStream()));

         input = null;
         sb = new StringBuilder();
         while ((input = br2.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br2.close();
         
         
         fromFile = new Scanner(new File("mus.mus"));
         temp = null;
         ArrayList<MusicAlbum> albums = new ArrayList<>();
          while(fromFile.hasNext()){
              try{
              if(fromFile.next().contains("artist")){
                  for (int i = 0; i < 3; i++) {
                      temp = fromFile.next();
                  }
                  String artist = null;
                  if(temp.contains("</span>")){
                      artist = temp.substring(temp.indexOf("\">")+2,temp.lastIndexOf("</span>"));
                  }
                  else{
                    temp = temp.substring(temp.indexOf("\">")+2);
                    artist = temp;
                    while(!(temp = fromFile.next()).contains("</span>")){
                        artist = artist + " " + temp;
                    }
                    artist = artist + " " + temp.substring(0,temp.lastIndexOf("</span"));
                  }
                  if(artist.contains("<") || artist.contains(">")){
                      artist = null;
                  }
                  while(!(temp = fromFile.next()).contains("black-bg-font")){}
                  String album = null;
                  if(temp.contains("</span>")){
                      try{
                        album = temp.substring(0, temp.lastIndexOf("</span>"));
                      }
                      catch(StringIndexOutOfBoundsException ex){}
                  }
                  else{
                    temp = temp.substring(temp.indexOf("\">")+2);
                    album = temp;
                    while(!(temp = fromFile.next()).contains("</span>")){
                        album = album + " " + temp;
                        }
                        try{
                            album = album + " " + temp.substring(0,temp.lastIndexOf("</span"));
                        }
                        catch(StringIndexOutOfBoundsException ex){}
                    }
                  if(album!=null)
                    if(album.contains("<")||album.contains(">")){
                        album = null;
                    }
                  while(!(temp = fromFile.next()).contains("justify-content-end")){}
                  temp = fromFile.next();
                  String releaseDate;
                  temp = temp + " " + fromFile.next();
                  fromFile.next();
                  temp = temp + " " + fromFile.next();
                  temp = temp.replace("<br", ",");
                  releaseDate = temp;
                  try{
                      albums.add(new MusicAlbum(genre, album, artist, releaseDate));
                  }
                  catch(IllegalArgumentException ex){}
              }
              }
              catch(NoSuchElementException exe){}
          }
          // Select random album
          MusicAlbum rec = albums.get(rand.nextInt(albums.size()));
          String albumName = rec.getAlbum().replace(".", "")
                  .replace("/", "-")
                  .replace(":","");
          String artistName = rec.getArtist().replace(".","")
                  .replace("/","-")
                  .replace(":","");

          URL = ("https://www.newreleasesnow.com/album/"+artistName.replace(" ", "-")+"-"+albumName
                  .replace(" ","-"))
                  .replace("(", "")
                  .replace(")", "")
                  .replace("’", "")
                  .replace("\'","")
                  .replace("-&-", "-")
                  .replace("-+-","-");
          rec.setLink(URL);
            final String httpsUrl3 = URL;
        final URL url3 = new URL(httpsUrl3);
        con = new URL(URL).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        con.connect();
         toFile = new PrintWriter(new File("mus.mus"));
         final BufferedReader br3 = new BufferedReader(new InputStreamReader(con.getInputStream()));

         input = null;
         sb = new StringBuilder();
         while ((input = br3.readLine()) != null){
           sb.append(input);
           sb.append("\n");
         }
          toFile.append(sb);
          toFile.close();
         br3.close();
         
         
         fromFile = new Scanner(new File("mus.mus"));
         temp = null;
          while(fromFile.hasNext()){
              if((temp = fromFile.next()).contains("500w")){
                  if((temp = fromFile.next()).contains("https")){
                      rec.setImg(temp);
                  }
              }
          }
            
          EmbedBuilder embed = new EmbedBuilder();
          embed.setTitle(genre + " Album Recommendation");
          embed.appendDescription("**Title:** " + rec.getAlbum() + "\n"
                  + "**Artist:** " + rec.getArtist() + "\n"
                  + "**Release Date:** " + rec.getReleaseDate() + "\n\n"
                  + "[Link](" + rec.getLink() + ")");
          embed.setColor(Color.GREEN.brighter());
          embed.setImage(rec.getImg());
          (new File("mus.mus")).delete();
          return embed;
        }
        catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
    private File downloadFile(String URL, String name){
        try{
            URL website = new URL(URL);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(name);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        }
        catch(IOException e){}
        return new File(name);
    }
    private void statTrack(String comm){
        Scanner fromFile=null, test = null;
        PrintWriter toFile = null;
        try{
            fromFile = new Scanner(new File("stat.stat"));
            //toFile = new PrintWriter(new File("stat.stat"));
            test = new Scanner(new File("token.token"));
        }
        catch(FileNotFoundException ex){
            try{
                new File("stat.stat").createNewFile();
                fromFile = new Scanner(new File("stat.stat"));
                //toFile = new PrintWriter(new File("stat.stat"));
            }
            catch(FileNotFoundException e){}
            catch(IOException e){}
        }
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        while(fromFile.hasNext()){
            String temp = fromFile.next();
            if(temp.equals(comm)){
                sb.append(temp);
                sb.append(" "+(fromFile.nextInt()+1));
                sb.append("\n");
                found = true;
            }
            else{
                sb.append(temp);
                sb.append(" "+fromFile.nextInt());
                sb.append("\n");
            }
        }
        if(!found){
            sb.append(comm);
            sb.append(" 1");
        }
        try{
            toFile = new PrintWriter(new File("stat.stat"));
        }
        catch(FileNotFoundException ex){
            try{
                new File("stat.stat").createNewFile();
                toFile = new PrintWriter(new File("stat.stat"));
            }
            catch(FileNotFoundException e){}
            catch(IOException e){}
        }
        toFile.write(sb.toString());
        toFile.close();
    }
    
    
}
