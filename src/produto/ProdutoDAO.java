package produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class ProdutoDAO {

	private Session sessao;
	private Transaction transacao;

	public void salvar(Produto produto) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(produto);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi poss�vel inserir a categoria. Erro: "
					+ e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar opera��o de inser��o. Mensagem: "
								+ e.getMessage());
			}
		}
	}

	public void atualizar(Produto produto) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(produto);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi poss�vel alterar a categoria. Erro: "
					+ e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar opera��o de atualiza��o. Mensagem: "
								+ e.getMessage());
			}
		}
	}

	public void excluir(Produto produto) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(produto);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("N�o foi poss�vel excluir a categoria. Erro: "
					+ e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar opera��o de exclus�o. Mensagem: "
								+ e.getMessage());
			}
		}
	}

	public Produto buscaCategoria(Integer codigo) {
		Produto produto = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			filtro.add(Restrictions.eq("categoria", codigo));
			produto = (Produto) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar opera��o de busca. Mensagem: "
								+ e.getMessage());
			}
		}
		return produto;
	}

	public List<Produto> listar() {
		List<Produto> categorias = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			categorias = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out
						.println("Erro ao fechar opera��o de listagem. Mensagem: "
								+ e.getMessage());
			}
		}
		return categorias;
	}
}
