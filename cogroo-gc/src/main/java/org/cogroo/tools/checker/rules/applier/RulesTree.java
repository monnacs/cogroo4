/**
 * Copyright (C) 2008 CoGrOO Team (cogroo AT gmail DOT com)
 * 
 * CoGrOO Team (cogroo AT gmail DOT com)
 * LTA, PCS (Computer and Digital Systems Engineering Department),
 * Escola Politécnica da Universidade de São Paulo
 * Av. Prof. Luciano Gualberto, trav. 3, n. 380
 * CEP 05508-900 - São Paulo - SP - BRAZIL
 * 
 * http://cogroo.sourceforge.net/
 * 
 * This file is part of CoGrOO.
 * 
 * CoGrOO is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public as published by 
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CoGrOO is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with CoGrOO. If not, see <http://www.gnu.org/licenses/>.
 */

package org.cogroo.tools.checker.rules.applier;

import java.io.Serializable;
import java.util.List;

/**
 * Class that defines a Rules tree, which nodes are {@link State}s
 * 
 * @author Marcelo Suzumura
 */
public class RulesTree implements Serializable {

	/**
	 * Id.
	 */
	private static final long serialVersionUID = -8125427282612619074L;
	
	private List<State> rulesTree;
	
	public RulesTree(List<State> rulesTree) {
		this.rulesTree = rulesTree;
	}

	public State getRoot() {
		return this.rulesTree.get(0);
	}

}