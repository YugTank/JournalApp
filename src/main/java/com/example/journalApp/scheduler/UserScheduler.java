package com.example.journalApp.scheduler;

import com.example.journalApp.entity.Sentiment;
import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserRepositoryImpl;
import com.example.journalApp.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    //@Scheduled(cron="0 0 12 ? * SUN *")
    public void fetchAndSendEmail(){
        List<User> userList=userRepository.getUsersForSA();
        for(User user:userList){
            List<Sentiment> sentiments=user.getJournalEntries()
                                            .stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                                            .map(x->x.getSentiment()).toList();
            Map<Sentiment,Integer> sentimentCount=new HashMap<>();
            for(Sentiment sentiment:sentiments){
                if(sentiment!=null)
                    sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0)+1);
            Sentiment mostFrequent=null;
            int maxCount=0;
            for(Map.Entry<Sentiment,Integer> entry:sentimentCount.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount=entry.getValue();
                    mostFrequent=entry.getKey();
                }
            }
            if(mostFrequent!=null){
                try {
                    emailService.sendEmail(user.getEmail(), "Sentiment analysis of past 7 days", mostFrequent.toString());
                }
                catch(MailException e) {
                    log.error(e.getMessage());
                }
            }

            }
        }
    }
}
