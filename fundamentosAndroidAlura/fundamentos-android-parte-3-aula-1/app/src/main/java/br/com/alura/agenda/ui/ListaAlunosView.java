package br.com.alura.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        dao = new AlunoDAO();
    }

    public void confirmaRemocao(MenuItem item) {
        new AlertDialog.Builder(context).setTitle("Removendo aluno")
                .setMessage("Tem certeza?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("NÂO", null)
                .show();
    }
    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }
    private  void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }
    public void configuraAdapter(ListView listaDeAlunos) {

        listaDeAlunos.setAdapter(adapter);
    }
}
