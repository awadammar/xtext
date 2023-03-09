/**
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.testlanguages.lookaheadLang;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Look Ahead3</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead3#getX <em>X</em>}</li>
 *   <li>{@link org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead3#getZ <em>Z</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.testlanguages.lookaheadLang.LookaheadLangPackage#getLookAhead3()
 * @model
 * @generated
 */
public interface LookAhead3 extends Alts
{
  /**
   * Returns the value of the '<em><b>X</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>X</em>' attribute.
   * @see #setX(String)
   * @see org.eclipse.xtext.testlanguages.lookaheadLang.LookaheadLangPackage#getLookAhead3_X()
   * @model
   * @generated
   */
  String getX();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead3#getX <em>X</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>X</em>' attribute.
   * @see #getX()
   * @generated
   */
  void setX(String value);

  /**
   * Returns the value of the '<em><b>Z</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Z</em>' containment reference.
   * @see #setZ(LookAhead4)
   * @see org.eclipse.xtext.testlanguages.lookaheadLang.LookaheadLangPackage#getLookAhead3_Z()
   * @model containment="true"
   * @generated
   */
  LookAhead4 getZ();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.testlanguages.lookaheadLang.LookAhead3#getZ <em>Z</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Z</em>' containment reference.
   * @see #getZ()
   * @generated
   */
  void setZ(LookAhead4 value);

} // LookAhead3
