package ecorota.api.service.util;

import ecorota.api.service.domain.Coordenada;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

    private final int[][] tabuleiro;
    private final int[][] matriz;
    private static final int[] mLinha = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] mColuna = {-1, -1, 0, 1, 1, 1, 0, -1};
    private final int[] posInicial;
    private int valorPos;
    private final int[] posFinal;

    public Backtracking(int[][] matriz, int[] posInicial, int[] posFinal) {
        this.matriz = matriz;
        this.tabuleiro = new int[matriz.length][matriz[0].length];
        this.posInicial = posInicial;
        this.posFinal = posFinal;
        this.valorPos = calcularPos(posInicial[0], posInicial[1]);
    }

    public List<Coordenada> buscar() {
        tabuleiro[posInicial[0]][posInicial[1]] = 1;
        if (tenta(2, posInicial[0], posInicial[1], false)) { //Solução encontrada
            return solucao();
        } else {
            System.out.println("Sem solução!");
            return null;
        }
    }

    /*Tenta o passeio usando backtracking
     * i - Movimentos identificados por um n
     * linhaAtual - Linha atual no tabulerio
     * colunaAtual - Coluna atual no tabulerio
     * sucess - Indica se achou uma solução
     * */
    public boolean tenta(int i, int linhaAtual, int colunaAtual, boolean sucess) {
        int newLinhaAtual, newColunaAtual, mov = 0;
        do {
            newLinhaAtual = linhaAtual + mLinha[mov];
            newColunaAtual = colunaAtual + mColuna[mov];

            //Veridica se a nova Movimentação é valida no tabuleiro
            if (isValidMove(matriz, newLinhaAtual, newColunaAtual)) {
                if (tabuleiro[newLinhaAtual][newColunaAtual] == 0) {//Movimentação não visitado.
                    tabuleiro[newLinhaAtual][newColunaAtual] = i;//Registra movimento

                    if (encontrou(newLinhaAtual, newColunaAtual)) {//Solução encontrada, sucess = verdadeiro
                        sucess = true;
                        break;
                    }
                    //ponto final não encontrado,Tenta novo movimento
                    sucess = tenta(i + 1, newLinhaAtual, newColunaAtual, sucess);
                }
            }
            mov++;
        } while (mov < 8 && !sucess);//Repita até encontrar a solucao ou acabaram-se candidatos ao movimento
        if (!sucess) {//não é bem sucedido
            tabuleiro[linhaAtual][colunaAtual] = 0;//Apaga registro anterior
        }
        return sucess;
    }

    private boolean isValidMove(int[][] matrix, int x, int y) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int longe = calcularPos(x,y);
        if(valorPos > longe){
            valorPos = longe;
        }
        boolean isLonge = Math.abs(valorPos - longe) < 30;

        return isLonge && 0 <= x && x < rows && 0 <= y && y < cols && matrix[x][y] >= 1;
    }

    private int calcularPos(int x, int y){
        return Math.abs(posFinal[0] - x) + Math.abs(posFinal[1] - y);
    }
    private boolean encontrou(int x, int y) {
        return posFinal[0] == x && posFinal[1] == y;
    }


    public List<Coordenada> solucao() {
        var list = new ArrayList<Coordenada>();
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] != 0) {
                    var co = new Coordenada(matriz[i][j], i, j, tabuleiro[i][j]);
                    list.add(co);
                }
            }
        }
        return list;
    }

    //Imprime a solução encontrada
    public void solucaoPrint() {
        System.out.println("Tabuleiro " + tabuleiro.length + "x" + tabuleiro[0].length +
                " Partindo de (" + posInicial[0] + ", " + posInicial[1] + ")");
        System.out.println("Solução:");
        for (int i = 0; i < tabuleiro.length; i++) {

            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] != 0) {
                    System.out.print("\t[");
                    System.out.printf("(%4d, %4d) = %4d", i, j, tabuleiro[i][j]);
                    System.out.println("  ]\n");
                }

            }

        }
    }
}