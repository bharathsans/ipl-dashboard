package demo.ipl_dashboard.data;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import demo.ipl_dashboard.Model.Match;

 
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) {

      Match match = new Match();

      match.setId(Long.parseLong(matchInput.getId()));
      match.setCity(matchInput.getCity());

      match.setDate(LocalDate.parse(matchInput.getDate()));
      match.setPlayerOfMatch(matchInput.getPlayer_of_match());
      match.setVenue(matchInput.getVenue());

      String firstInningsTeam, secondInningsTeam;

      if("bat".equals(matchInput.getToss_decision())){
        firstInningsTeam = matchInput.getToss_winner();

        if(matchInput.getTeam1() == matchInput.getToss_winner())
            secondInningsTeam = matchInput.getTeam2();
        else  
            secondInningsTeam = matchInput.getTeam1();
      }
      else{
        secondInningsTeam = matchInput.getToss_winner();
        
        if(matchInput.getTeam1() == matchInput.getToss_winner())
          firstInningsTeam = matchInput.getTeam2();
        else  
          firstInningsTeam = matchInput.getTeam1();
      }
      
      match.setTeam1(firstInningsTeam);
      match.setTeam2(secondInningsTeam);

      match.setTossWinner(matchInput.getToss_winner());
      match.setTossDecision(matchInput.getToss_decision());

      match.setMatchWinner(matchInput.getWinner());
      match.setResult(matchInput.getResult());
      match.setResultMargin(matchInput.getResult_margin());
      
      match.setUmpire1(matchInput.getUmpire1());
      match.setUmpire2(matchInput.getUmpire2());

      

      return match;

  }

}
