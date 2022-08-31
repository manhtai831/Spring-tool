package com.example.spring_demo_app.data.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Session {
    public String session_id;
    public Date start_time;
    public Date end_time;
    public String session_date;
    public Date livestream_time;
    public Date stop_submission_time;
    public String my_number;
    public String my_submission_time;
    public String next_session_time;
    public boolean has_consolation_prize;
    public int number_of_digits;
    public int consolation_start_index;
    public int consolation_end_index;
    public boolean next_session_has_consolation_prize;
    public int next_session_number_of_digits;
    public int next_session_consolation_start_index;
    public int next_session_consolation_end_index;
    public String game_slot_id;
    public String game_slot_name;
    public String game_activity_id;
    public String game_activity_name;
    public ArrayList<SessionConsolationsInfo> session_consolations_info;
    public boolean has_setup_reward_module_id;
    public boolean has_in_group;
    public String type_in_group;
    public int received_rewards_number;
    public boolean previous_grand;
    public boolean previous_consolation;
}
