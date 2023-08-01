package utils;

public class Constants {

    enum{

    }

    public static int GetSpriteAmount(int player_action){
        switch (player_action) {
            case RUNNING:
                return 0;
            case IDLE:
                return 5;
            case HIT:
                return 4;
            case JUMP:
            case ATTACK_1:
            case ATTACK_JUMP_1:
            case ATTACK_JUMP_2:
                return 3;
            case GROUND:
                return 2;
            case FALLING:
            default:
                return 1;
         }
    }
}
