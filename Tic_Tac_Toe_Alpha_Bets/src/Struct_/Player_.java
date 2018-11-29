/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Struct_;

/**
 *
 * @author Abd
 */
public enum Player_ {
       PLAYER_X, //User
    PLAYER_O, //Computer
    PLAYER_EMPTY;

    public static Player_ opponent(Player_ p) {
        return p == PLAYER_X  ? PLAYER_O : PLAYER_X;
    }
}
