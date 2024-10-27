import java.util.Scanner;

public class Countdown
{
  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    
    //User Input
    System.out.print("Enter countdown time (\"hh:mm:ss\") : ");
    String user_input_timeout = scanner.nextLine();
    
    //Check if is valid format ("hh:mm:ss")
    if(Countdown.is_valid_time_format(user_input_timeout))
    {
      //Start clock and wait timeout
      int[] timeout = Countdown.extract_time_from_string(user_input_timeout);
      Countdown.start_clock_wait(timeout);
    }else
    {
      System.out.println("Invalid countdown time format");
    }
  }
  private static boolean is_valid_time_format(String hhmmss)
  {
    if(hhmmss.length() == "hh:mm:ss".length() && hhmmss.charAt(2) == ':' && hhmmss.charAt(5) == ':')
    {
      try
      {
        int hh = Integer.parseInt(hhmmss.substring(0,2));
        int mm = Integer.parseInt(hhmmss.substring(3,5));
        int ss = Integer.parseInt(hhmmss.substring(6,8));
        if(mm >= 0 && mm < 60 && ss >= 0 && ss < 60)
        {
          return true;
        }
      }catch(NumberFormatException e)
      {
        
      }
    }
    return false;
  }
  private static int[] extract_time_from_string(String hhmmss)
  {
    try
    {
      int hh = Integer.parseInt(hhmmss.substring(0,2));
      int mm = Integer.parseInt(hhmmss.substring(3,5));
      int ss = Integer.parseInt(hhmmss.substring(6,8));
      return new int[]{hh, mm, ss};
    }catch(NumberFormatException e)
    {

    }
    return null;
  }
  private static void start_clock_wait(int[] timeout)
  {
    int hh_timeout = timeout[0];
    int mm_timeout = timeout[1];
    int ss_timeout = timeout[2];
    
    while(hh_timeout > 0 || mm_timeout > 0 || ss_timeout > 0)
    {
      System.out.printf("%02d:%02d:%02d \r\r\r", hh_timeout, mm_timeout, ss_timeout);
      
      if(ss_timeout > 0)
      {
        ss_timeout--;
      }else {
        ss_timeout = 59;
        if(mm_timeout > 0)
        {
          mm_timeout--;
        }else {
          mm_timeout = 59;
          if(hh_timeout > 0)
          {
            hh_timeout--;
          }
        }
      }
      
      try
      {
        Thread.sleep(1000);
      }catch(InterruptedException e)
      {
        Thread.currentThread().interrupt();
      }
    }
  }
}
