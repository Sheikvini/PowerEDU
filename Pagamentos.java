 
package beans;

public class Pagamentos {
    private int id_pagamentos;
    private String ds_ncartao;
    private int dt_vencimentocartao;
    private String ds_cvc;
    private int ds_fromadepagamento;
    private int qtd_parcelas;

    public int getId_pagamentos() {
        return id_pagamentos;
    }

    public void setId_pagamentos(int id_pagamentos) {
        this.id_pagamentos = id_pagamentos;
    }

    public String getDs_ncartao() {
        return ds_ncartao;
    }

    public void setDs_ncartao(String ds_ncartao) {
        this.ds_ncartao = ds_ncartao;
    }

    public int getDt_vencimentocartao() {
        return dt_vencimentocartao;
    }

    public void setDt_vencimentocartao(int dt_vencimentocartao) {
        this.dt_vencimentocartao = dt_vencimentocartao;
    }

    public String getDs_cvc() {
        return ds_cvc;
    }

    public void setDs_cvc(String ds_cvc) {
        this.ds_cvc = ds_cvc;
    }

    public int getDs_fromadepagamento() {
        return ds_fromadepagamento;
    }

    public void setDs_fromadepagamento(int ds_fromadepagamento) {
        this.ds_fromadepagamento = ds_fromadepagamento;
    }

    public int getQtd_parcelas() {
        return qtd_parcelas;
    }

    public void setQtd_parcelas(int qtd_parcelas) {
        this.qtd_parcelas = qtd_parcelas;
    }

    
    
    
}
