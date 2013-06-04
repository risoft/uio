Ext.define('uio.UioRecordViewer', {
    extend: 'Ext.Panel',
    xtype: 'uiorecordviewer',
    
  constructor: function(config)
    {
	  var closeButton = {ui: 'back', text: 'Back',handler: function(button){  
		  var viewer = button.up("uiorecordviewer");
		  viewer.destroy();
	  }};

	 
	  var panel = {xtype: 'panel', tpl: uio.formdefs[config.jclass].tpl, data: config.data};
	  
	  config.items = [{docked: 'top', xtype: 'toolbar', title: uio.formdefs[config.jclass].title, items:[closeButton]}, panel];
	  
      this.callParent(arguments);
    }
});
