
package dominio;

public class Guerrero extends Casta {

	private static final int ENERGIAMINIMA = 10;
	private static final int MULTIPLICADORFUERZA = 2;
	private static final int BONUSFUERZA = 5;

	/** La clase Guerrero es una casta de Personaje, hereda de la clase Casta.
	 * posee dos constructores, el constructor por defecto,
	 * llama al constructor por defecto de la clase padre (Casta),
	 * luego inicializa la variable nombreCasta y crea un array de String de tamaño 3
	 * El otro constructor, llama al constructor de la clase padre (Casta),
	 * pasándole los argumentos recibidos
	 * por el constructor hijo y luego inicializa la variable nombreCasta
	 * @param prob_crit Probabilidad de que el personaje realice un golpe crítico
	 * @param evasion Probabilidad de que el personaje evite un golpe crítico
	 * @param daño_crit Valor por el cual será multiplicado el golpe básico
	 */
	public Guerrero(final double prob_crit, final double evasion, final double daño_crit) {
		super(prob_crit, evasion, daño_crit);
	}

	public Guerrero() {
		super();
	}


	@Override
	public final boolean habilidad1(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > ENERGIAMINIMA) {
			caster.setEnergia(caster.getEnergia() - ENERGIAMINIMA);
			if (atacado.serAtacado(caster.getAtaque() * MULTIPLICADORFUERZA) > 0)
				return true;
		}
		return false;
	}


	/** Retorna un booleano dependiendo de si se realizó exitosamente o no el ataque.
	 * La primera condición para que el ataque pueda realizarse es que el atacante(caster) posea 10 o
	 * más del atributo energia ya que estos se descuentan seguido de comprobar que los posee,
	 *  de lo contrario el ataque no será posible y se retornará false
	 * @param caster Personaje que realiza el ataque, en este caso no es un ataque si no un aumento en
	 * las caracteristicas del caster en este caso la defensa (se agrega a la defensa actual el atributo
	 * magia)
	 * @param atacado no cumple función alguna dentro de este método
	 */
	@Override
	public final boolean habilidad2(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > ENERGIAMINIMA) {
			caster.setEnergia(caster.getEnergia() - ENERGIAMINIMA);
			caster.setDefensa(caster.getDefensa() + caster.getMagia());
			return true;
		}
		return false;
	}


	/** Retorna un booleano dependiendo de si se realizó exitosamente o no el ataque.
	 * La primera condición para que el ataque pueda realizarse es que el atacante(caster) posea 10 o
	 * más del atributo energia ya que estos se descuentan seguido de comprobar que los posee,
	 * de lo contrario el ataque no será posible y se retornará false.
	 * Éste método sólo retornará true si el argumento atacado, es una instancia de Personaje.
	 * @param caster Personaje atacante
	 * @param atacado Éste argumento debe de ser clase atacado para que el método tenga posibilidad
	 * de retornar true
	 */
	@Override
	public final boolean habilidad3(final Personaje caster, final Peleable atacado) {
		if (caster.getEnergia() > ENERGIAMINIMA) {
			caster.setEnergia(caster.getEnergia() - ENERGIAMINIMA);
			if (atacado instanceof Personaje) {
				int defensa_original = ((Personaje) atacado).getDefensa();
				((Personaje) atacado).setDefensa(0);
				if (atacado.serAtacado(caster.getAtaque()) > 0) {
					((Personaje) atacado).setDefensa(defensa_original);
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public final int recibirFuerzaBonus() {
		return BONUSFUERZA;

	}

	@Override
	public final int recibirDestrezaBonus() {

		return 0;
	}

	@Override
	public final int recibirInteligenciaBonus() {
		return 0;
	}

	@Override
	public final String getNombreCasta() {
		return "Guerrero";
	}

	@Override
	public final String[] getHabilidadesCasta() {
		return new String[] {"Ataque Doble","Aumentar Defensa","Ignorar Defensa"};
	}
}


