/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
    

/**
 *
 * @author Lucas
 */
public class PilhaVetorEncadeada<T> {

    ListaEncadeada<T> lista = new ListaEncadeada();

    public PilhaVetorEncadeada() {

    }

    public void push(T elemento) {
        lista.inserir(elemento);

    }

    public T pop() {
        T valorRetirar = peek();
        lista.retirar(valorRetirar);
        return valorRetirar;
    }

    public T peek() {
        if (lista.estaVazia()) {
            throw new RuntimeException("Lista vazia");
        }
        NoLista<T> ultimoNmero = lista.getPrimeiro();
        return ultimoNmero.getInfo();
    }

    public boolean estaVazia() {
        if (lista.getPrimeiro() == null) {
            return true;
        } else {
            return false;
        }
    }

    public void liberar() {
        while (!lista.estaVazia()) {
            pop();
        }
    }

    @Override
    public String toString() {
        String resultado = "";
        NoLista<T> atual = lista.getPrimeiro();
        while (atual != null) {
            resultado += atual.getInfo().toString() + "\n";
            atual = atual.getProximo();
        }
        return resultado;
    }

    public PilhaVetorEncadeada<T> PilhacomEndTags(PilhaVetorEncadeada<T> pilhaEndTags) {
        if (estaVazia()) {
            throw new NullPointerException("Parâmetro 'pilha' vazio.");
        }
        NoLista<T> tagInicio = lista.getPrimeiro();
        while (tagInicio != null) {
            Tag tagAtual = (Tag) tagInicio.getInfo();
            if (tagAtual.isFechamento()) {
                pilhaEndTags.push((T) tagInicio.getInfo());
                lista.retirar((T) tagInicio.getInfo());
            }
            tagInicio = tagInicio.getProximo();
        }
        return pilhaEndTags;
    }

    public void SoTagHTML(String linha, PilhaVetorEncadeada<T> pilhaTags) {
        if (linha.contains("<") && linha.contains(">")) {
            int inicioTag = linha.indexOf("<");
            int finalTag = linha.indexOf(">");
            String tagContent = linha.substring(inicioTag + 1, finalTag);
            if (tagContent.contains(" ")) {
                tagContent = tagContent.split(" ")[0];
            }
            if (tagContent.startsWith("/")) {

                pilhaTags.push((T) new Tag(tagContent));
            } else {
                pilhaTags.push((T) new Tag(tagContent));
            }
            SoTagHTML(linha.substring(finalTag + 1), pilhaTags);
        }
    }

    public PilhaVetorEncadeada<Tag> agruparTags() {
        PilhaVetorEncadeada<Tag> pilhaAgrupada = new PilhaVetorEncadeada<>();
        ListaEncadeada<Tag> listaAgrupada = new ListaEncadeada<>();

        NoLista<T> atual = lista.getPrimeiro();
        while (atual != null) {
            Tag tagAtual = (Tag) atual.getInfo();
            boolean encontrada = false;

            NoLista<Tag> atualLista = listaAgrupada.getPrimeiro();
            while (atualLista != null) {
                if (atualLista.getInfo().getTag().equalsIgnoreCase(tagAtual.getTag())) {
                    atualLista.getInfo().setCount(atualLista.getInfo().getCount() + tagAtual.getCount());
                    encontrada = true;
                    break;
                }
                atualLista = atualLista.getProximo();
            }
            if (!encontrada) {
                listaAgrupada.inserir(new Tag(tagAtual.getTag(), tagAtual.getCount()));
            }

            atual = atual.getProximo();
        }
        NoLista<Tag> atualLista = listaAgrupada.getPrimeiro();
        while (atualLista != null) {
            pilhaAgrupada.push(atualLista.getInfo());
            atualLista = atualLista.getProximo();
        }

        return pilhaAgrupada;
    }
    /*
    private ListaEncadeada<String> getSingletonTags() {
        ListaEncadeada<String> singletonTags = new ListaEncadeada<>();
        singletonTags.inserir("meta");
        singletonTags.inserir("base");
        singletonTags.inserir("br");
        singletonTags.inserir("col");
        singletonTags.inserir("command");
        singletonTags.inserir("embed");
        singletonTags.inserir("hr");
        singletonTags.inserir("img");
        singletonTags.inserir("input");
        singletonTags.inserir("link");
        singletonTags.inserir("param");
        singletonTags.inserir("source");
        singletonTags.inserir("!DOCTYPE");
        return singletonTags;
    } */

    public ListaEncadeada<T> getLista() {
        return lista;
    }

    public boolean listaContem(Tag tag) {
        NoLista<T> atual = lista.getPrimeiro();
        while (atual != null) {
            Tag tagAtual = (Tag) atual.getInfo();
            if (tagAtual.getTag().equals(tag.getTag())) {
                return true;
            }
            atual = atual.getProximo();
        }
        return false;
    }

    public boolean validarTagsFechamento(PilhaVetorEncadeada<Tag> pilhaEndTags) {
        if (this.estaVazia() || pilhaEndTags.estaVazia()) {
            throw new RuntimeException("Pilhas de tags ou tags de fechamento estão vazias.");
        }

        NoLista<T> ponteiroPilhaStart = this.lista.getPrimeiro();
        NoLista<Tag> ponteiroPilhaEnd = pilhaEndTags.lista.getPrimeiro();

        while (ponteiroPilhaStart != null) {
            Tag StartTag = (Tag) ponteiroPilhaStart.getInfo();
            boolean tagFechamentoEncontrada = false;

            while (ponteiroPilhaEnd != null) {
                Tag tagFechamento = ponteiroPilhaEnd.getInfo();
                String tagAberturaEsperada = tagFechamento.getTag().substring(1);

                if (StartTag.getTag().equalsIgnoreCase("br") || StartTag.getTag().equalsIgnoreCase("meta") || StartTag.getTag().equalsIgnoreCase("base") || StartTag.getTag().equalsIgnoreCase("col") ||
                        StartTag.getTag().equalsIgnoreCase("command") || StartTag.getTag().equalsIgnoreCase("embed") || StartTag.getTag().equalsIgnoreCase("hr") || StartTag.getTag().equalsIgnoreCase("img") ||
                        StartTag.getTag().equalsIgnoreCase("input") || StartTag.getTag().equalsIgnoreCase("link") || StartTag.getTag().equalsIgnoreCase("param") || StartTag.getTag().equalsIgnoreCase("source") ||
                        StartTag.getTag().equalsIgnoreCase("!DOCTYPE")) {
                    tagFechamentoEncontrada = true;
                    this.pop();
                    break;
                }

                if (StartTag.getTag().equalsIgnoreCase(tagAberturaEsperada)) {
                    tagFechamentoEncontrada = true;
                    this.pop();
                    pilhaEndTags.lista.retirar(tagFechamento);
                    break;
                }

                ponteiroPilhaEnd = ponteiroPilhaEnd.getProximo();
            }

            if (!tagFechamentoEncontrada) {
                throw new RuntimeException("Falta a tag de fechamento para: <" + StartTag.getTag() + ">" + "\n" + "Tag esperada: </" + StartTag.getTag() + "> \n");
            }

            ponteiroPilhaStart = ponteiroPilhaStart.getProximo();
            ponteiroPilhaEnd = pilhaEndTags.lista.getPrimeiro();
        }

        return true;
    }

    public boolean validarTagsAbertura(PilhaVetorEncadeada<Tag> pilhaEndTags, PilhaVetorEncadeada<Tag> pilhaStartTags) {
        if (this.estaVazia() || pilhaEndTags.estaVazia()) {
            throw new RuntimeException("Pilhas de tags ou tags de abertura estão vazias.");
        }

        NoLista<Tag> ponteiroPilhaEnd = pilhaEndTags.lista.getPrimeiro();
        NoLista<T> ponteiroPilhaStart = (NoLista<T>) pilhaStartTags.lista.getPrimeiro();

        while (ponteiroPilhaEnd != null) {
            Tag endTag = ponteiroPilhaEnd.getInfo();
            boolean tagAberturaEncontrada = false;

            while (ponteiroPilhaStart != null) {
                Tag startTag = (Tag) ponteiroPilhaStart.getInfo();
                String tagFechamentoEsperada = "/" + startTag.getTag();

                if (endTag.getTag().equalsIgnoreCase(tagFechamentoEsperada)) {
                    tagAberturaEncontrada = true;
                    pilhaEndTags.pop();
                    pilhaStartTags.lista.retirar(startTag);
                    break;
                }

                ponteiroPilhaStart = ponteiroPilhaStart.getProximo();
            }

            if (!tagAberturaEncontrada) {
                throw new RuntimeException("Falta a tag de abertura para: <" + endTag.getTag() + ">" + "\n" + "Tag esperada: <" + endTag.getTag().substring(1) + "> \n");
            }

            ponteiroPilhaEnd = ponteiroPilhaEnd.getProximo();
            ponteiroPilhaStart = (NoLista<T>) pilhaStartTags.lista.getPrimeiro();
        }

        return true;
    }
}
