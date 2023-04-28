package org.example;

public class CentroDistribuicao {

    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO
    }

    public SITUACAO situacao;
    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private int tAditivo;
    private int tGasolina;
    private int tAlcool1;
    private int tAlcool2;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        if (tAditivo <= MAX_ADITIVO) {
            this.tAditivo = tAditivo;
        }

        if (tGasolina <= MAX_GASOLINA) {
            this.tGasolina = tGasolina;
        }

        if ((tAlcool1 + tAlcool2) <= MAX_ALCOOL && tAlcool1 == tAlcool2) {
            this.tAlcool1 = tAlcool1;
            this.tAlcool2 = tAlcool2;
        }

    }

    public void defineSituacao() {
        // atualiza a situação
        double perAditivo = tAditivo / MAX_GASOLINA;
        double perGasolina = tGasolina / MAX_GASOLINA;
        double perAlcool = (tAlcool1 + tAlcool2) / MAX_ALCOOL;

        if (perAditivo >= 0.5 && perGasolina >= 0.5 && perAlcool >= 0.5) {
            this.situacao = SITUACAO.NORMAL;
        } else if (perAditivo < 0.25 || perGasolina < 0.25 || perAlcool < 0.25) {
            this.situacao = SITUACAO.EMERGENCIA;
        } else {
            this.situacao = SITUACAO.SOBRAVISO;
        }

    }

    public SITUACAO getSituacao() {
        return situacao;
    }

    public int gettGasolina() {
        return tGasolina;
    }

    public int gettAditivo() {
        return tAditivo;
    }

    public int gettAlcool1() {
        return tAlcool1;
    }

    public int gettAlcool2() {
        return tAlcool2;
    }

    public int recebeAditivo(int qtdade) {

        if ((qtdade + gettAditivo() < MAX_ADITIVO)) {
            this.tAditivo += qtdade;
            return qtdade;
        } else {
            return -1;
        }

    }

    public int recebeGasolina(int qtdade) {
        
        if ((qtdade + gettGasolina() < MAX_GASOLINA)) {
            this.tGasolina += qtdade;
            return qtdade;
        } else {
            return -1;
        }
        
    }

    public int recebeAlcool(int qtdade) {
        

        if ((qtdade + gettAlcool1() < MAX_ADITIVO)) {
            this.tAditivo += qtdade;
            return qtdade;
        } else {
            return -1;
        }

    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        //se pode sera tendido, retorna array com a quantiade de combustivel restante em cada tanque depois
            //na ordem -> aditivo, gasolina, alcoolt1, alcoolt2
        
        //se recebe valor invalido retorna -7
        //se nao puder ser atendido por causa da situacao, retorna -14
        //caso nao tenha combustivel suficiente para completar a mistura, retorna -21
        
        // quandide de gasolina solicitada
        // quanto de cada vai precisar
        // temos o suficioente?

        SITUACAO situ = getSituacao();

        double qtdGasolina = qtdade * 0.7;
        double qtdAditivo = qtdade * 0.05;
        double qtdAlcool = qtdade * 0.25;

        int qntdCombustivelDisponivel = 

        int[] retornoArr = new int[4];

        if (qtdade <= 0) {
            retornoArr[0] = -7;
            return retornoArr;
        }

        if (qtdAditivo > gettAditivo()) {
            retornoArr[0] = -21;
            return retornoArr;
        } else if (qtdAlcool > gettAlcool1() + gettAlcool2()) {
            retornoArr[0] = -21;
            return retornoArr;
        } else if (qtdGasolina > gettGasolina()) {
            retornoArr[0] = -21;
            return retornoArr;
        }

        switch(situ){
            case NORMAL: 

            case SOBRAVISO: {
                //qtdade solicitade tem que ser 50% do disponivel
                if (tipoPosto == TIPOPOSTO.COMUM) {
                    qtdade = qtdade/2;
                }

            }
            case EMERGENCIA:
        }


        return retornoArr;

    }
}