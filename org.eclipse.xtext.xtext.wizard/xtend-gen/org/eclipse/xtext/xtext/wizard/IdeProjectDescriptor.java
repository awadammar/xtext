/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.xtext.wizard;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xtext.wizard.BuildSystem;
import org.eclipse.xtext.xtext.wizard.ExternalDependency;
import org.eclipse.xtext.xtext.wizard.Outlet;
import org.eclipse.xtext.xtext.wizard.PomFile;
import org.eclipse.xtext.xtext.wizard.ProjectDescriptor;
import org.eclipse.xtext.xtext.wizard.RuntimeProjectDescriptor;
import org.eclipse.xtext.xtext.wizard.SourceLayout;
import org.eclipse.xtext.xtext.wizard.WizardConfiguration;

@FinalFieldsConstructor
@SuppressWarnings("all")
public class IdeProjectDescriptor extends ProjectDescriptor {
  @Override
  public String getNameQualifier() {
    return ".ide";
  }
  
  @Override
  public Set<? extends ProjectDescriptor> getUpstreamProjects() {
    WizardConfiguration _config = this.getConfig();
    RuntimeProjectDescriptor _runtimeProject = _config.getRuntimeProject();
    return Collections.<ProjectDescriptor>unmodifiableSet(CollectionLiterals.<ProjectDescriptor>newHashSet(_runtimeProject));
  }
  
  @Override
  public boolean isEclipsePluginProject() {
    return (Objects.equal(this.getConfig().getPreferredBuildSystem(), BuildSystem.NONE) || this.getConfig().getUiProject().isEnabled());
  }
  
  @Override
  public boolean isPartOfGradleBuild() {
    return true;
  }
  
  @Override
  public boolean isPartOfMavenBuild() {
    return true;
  }
  
  @Override
  public Set<ExternalDependency> getExternalDependencies() {
    LinkedHashSet<ExternalDependency> _xblockexpression = null;
    {
      final LinkedHashSet<ExternalDependency> deps = CollectionLiterals.<ExternalDependency>newLinkedHashSet();
      Set<ExternalDependency> _externalDependencies = super.getExternalDependencies();
      Iterables.<ExternalDependency>addAll(deps, _externalDependencies);
      ExternalDependency _createXtextDependency = ExternalDependency.createXtextDependency("org.eclipse.xtext.ide");
      deps.add(_createXtextDependency);
      ExternalDependency _createXtextDependency_1 = ExternalDependency.createXtextDependency("org.eclipse.xtext.xbase.ide");
      deps.add(_createXtextDependency_1);
      _xblockexpression = deps;
    }
    return _xblockexpression;
  }
  
  @Override
  public PomFile pom() {
    PomFile _pom = super.pom();
    final Procedure1<PomFile> _function = (PomFile it) -> {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<build>");
      _builder.newLine();
      {
        if (((!this.isEclipsePluginProject()) && Objects.equal(this.getConfig().getSourceLayout(), SourceLayout.PLAIN))) {
          _builder.append("\t");
          _builder.append("<sourceDirectory>");
          String _sourceFolder = this.sourceFolder(Outlet.MAIN_JAVA);
          _builder.append(_sourceFolder, "\t");
          _builder.append("</sourceDirectory>");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("<resources>");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("<resource>");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("<directory>");
          String _sourceFolder_1 = this.sourceFolder(Outlet.MAIN_RESOURCES);
          _builder.append(_sourceFolder_1, "\t\t\t");
          _builder.append("</directory>");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("<excludes>");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t\t");
          _builder.append("<exclude>**/*.java</exclude>");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t\t");
          _builder.append("<exclude>**/*.xtend</exclude>");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("</excludes>");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("</resource>");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("</resources>");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("<plugins>");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("<plugin>");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("<groupId>org.eclipse.xtend</groupId>");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("<artifactId>xtend-maven-plugin</artifactId>");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("</plugin>");
      _builder.newLine();
      {
        boolean _isEclipsePluginProject = this.isEclipsePluginProject();
        boolean _not = (!_isEclipsePluginProject);
        if (_not) {
          _builder.append("\t\t");
          _builder.append("<plugin>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t");
          _builder.append("<groupId>org.codehaus.mojo</groupId>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t");
          _builder.append("<artifactId>build-helper-maven-plugin</artifactId>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t");
          _builder.append("<version>1.9.1</version>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t");
          _builder.append("<executions>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t");
          _builder.append("<execution>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t");
          _builder.append("<id>add-source</id>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t");
          _builder.append("<phase>initialize</phase>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t");
          _builder.append("<goals>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t");
          _builder.append("<goal>add-source</goal>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t");
          _builder.append("<goal>add-resource</goal>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t");
          _builder.append("</goals>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t");
          _builder.append("<configuration>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t");
          _builder.append("<sources>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t\t");
          _builder.append("<source>");
          String _sourceFolder_2 = this.sourceFolder(Outlet.MAIN_SRC_GEN);
          _builder.append(_sourceFolder_2, "\t\t\t\t\t\t\t");
          _builder.append("</source>");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t");
          _builder.append("</sources>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t");
          _builder.append("<resources>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t\t");
          _builder.append("<resource>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t\t\t");
          _builder.append("<directory>");
          String _sourceFolder_3 = this.sourceFolder(Outlet.MAIN_SRC_GEN);
          _builder.append(_sourceFolder_3, "\t\t\t\t\t\t\t\t");
          _builder.append("</directory>");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t\t\t");
          _builder.append("<excludes>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("<exclude>**/*.java</exclude>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("<exclude>**/*.g</exclude>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t\t\t");
          _builder.append("</excludes>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t\t");
          _builder.append("</resource>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t\t");
          _builder.append("</resources>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t\t");
          _builder.append("</configuration>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t\t");
          _builder.append("</execution>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\t");
          _builder.append("</executions>");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("</plugin>");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("</plugins>");
      _builder.newLine();
      _builder.append("</build>");
      _builder.newLine();
      it.setBuildSection(_builder.toString());
      String _xifexpression = null;
      boolean _isEclipsePluginProject_1 = this.isEclipsePluginProject();
      if (_isEclipsePluginProject_1) {
        _xifexpression = "eclipse-plugin";
      } else {
        _xifexpression = "jar";
      }
      it.setPackaging(_xifexpression);
    };
    return ObjectExtensions.<PomFile>operator_doubleArrow(_pom, _function);
  }
  
  public IdeProjectDescriptor(final WizardConfiguration config) {
    super(config);
  }
}
